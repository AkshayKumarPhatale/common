package com.accenture.empportal.resilience4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.accenture.empportal.entity.Department;
import com.accenture.empportal.entity.Employee;
import com.accenture.empportal.exception.DepartmentNotFoundException;
import com.accenture.empportal.exception.NoEmployeesFoundException;
import com.accenture.empportal.exception.ServerException;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class AdminServiceResilience4j {

	@Autowired
	private WebClient webClient;

	@CircuitBreaker(name = "findByEmpIdCircuitBreaker", fallbackMethod = "fallbackForFindByEmpId")
	public Mono<Employee> findByEmpId(Long id) {

		Mono<Employee> employee = webClient.get().uri("http://localhost:2020/api/employee/" + id).retrieve()
				.onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new RuntimeException("API not found")))
				.onStatus(HttpStatus::is5xxServerError,
						error -> Mono.error(new ServerException("ServerException")))
				.bodyToMono(Employee.class);

		employee.onErrorStop();

		Department department = webClient.get()
				.uri("http://localhost:2121/api/department/" + employee.block().getDeptId()).retrieve()
				.onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new RuntimeException("API not found")))
				.onStatus(HttpStatus::is5xxServerError,
						error -> Mono.error(new DepartmentNotFoundException("dept  Id not Found")))
				.bodyToMono(Department.class).block();

		Employee emp = employee.block();
		emp.setDept(department);

		return Mono.just(emp);

	}

	public Mono<Employee> fallbackForFindByEmpId(Exception  e) {
		System.out.println("inside fallbackForFindByEmpId"+e.getLocalizedMessage());
		return Mono.error(new ServerException("Server Exception"));
	}

	
	@CircuitBreaker(name = "findAllCircuitBreaker",fallbackMethod="fallbackForfindAll")
	public Flux<Employee> findAll() {
		System.out.println("findall resilience");
		return webClient.get()
				.uri("http://localhost:2020/api/employee/findall").retrieve()
				.onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new RuntimeException("API not found")))
				.onStatus(HttpStatus::is5xxServerError,
						error -> Mono.error(new NoEmployeesFoundException("No Employees found")))
				.bodyToFlux(Employee.class);

	}
	
	
	public Flux<Employee> fallbackForfindAll(Throwable  e) {
		
		Flux<Employee> f= Flux.error(new ServerException("Server Exception"));
		
		return f;
	}

}
