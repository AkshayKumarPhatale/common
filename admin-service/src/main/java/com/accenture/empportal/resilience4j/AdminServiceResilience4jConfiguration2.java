package com.accenture.empportal.resilience4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.accenture.empportal.entity.Department;
import com.accenture.empportal.entity.Employee;
import com.accenture.empportal.exception.DepartmentNotFoundException;
import com.accenture.empportal.exception.EmployeeNotFoundException;
import com.accenture.empportal.exception.NoEmployeesFoundException;
import com.accenture.empportal.exception.ServerException;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class AdminServiceResilience4jConfiguration2 {

	@Autowired
	private WebClient webClient;

	// private static final ExecutorService executorService =
	// Executors.newCachedThreadPool();

	private static final Logger logger = Logger.getLogger(AdminServiceResilience4jConfiguration2.class.getName());

	Employee employee = new Employee();
	Department department = new Department();

	@CircuitBreaker(name = "findByEmpIdCircuitBreaker", fallbackMethod = "fallbackForFindByEmpId")
	@RateLimiter(name = "findByEmpIdRateLimiter", fallbackMethod = "fallbackForRateLimiter")
	public Mono<Employee> findByEmpId(Long id) {

			return CompletableFuture.supplyAsync(() -> {
				logger.log(Level.INFO, () -> "Request from service " + id);

				return webClient.get().uri("http://EMPLOYEE-SERVICE/api/employee/" + id).retrieve()
						.onStatus(HttpStatus::is5xxServerError,
								error -> Mono.error(new EmployeeNotFoundException("Employee not Found with this id")))
						.onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new RuntimeException("API not found")))
						.bodyToMono(Employee.class);

			})

					.thenApplyAsync(

							(employeedto) -> {
								Employee emp = employeedto.block();
								logger.log(Level.INFO, () -> "Response from employee service" + emp);
								BeanUtils.copyProperties(emp, employee);
								Department dept = webClient.get()
										.uri("http://DEPARTMENT-SERVICE/api/department/" + emp.getDeptId()).retrieve()
										.onStatus(HttpStatus::is5xxServerError,
												error -> Mono.error(new DepartmentNotFoundException(
														"Department not Found with this id")))
										.bodyToMono(Department.class).block();

								BeanUtils.copyProperties(dept, department);
								employee.setDept(department);
								logger.log(Level.INFO, () -> " Final Response" + emp);
								return Mono.just(employee);

							}).join();
					

		} 
		
		


	public Mono<Employee> fallbackForFindByEmpId(Exception e) {
		logger.log(Level.INFO, () -> "inside fallbackForFindByEmpId" + e.getLocalizedMessage());
		return Mono.error(new ServerException(e.getLocalizedMessage()));
	}

	public Mono<Employee> fallbackForRateLimiter(Exception e) {
		logger.log(Level.INFO, () -> "inside rateLimiterfallback method" + e.getLocalizedMessage());
		return Mono.error(new ServerException("Server Exception" + e.getLocalizedMessage()));
	}
	
	
	@CircuitBreaker(name = "findAllCircuitBreaker", fallbackMethod = "fallbackForfindAll")
	public Flux<Employee> findAll() {
		logger.log(Level.INFO, () -> "From findAll method");
		return webClient.get().uri("http://localhost:2020/api/employee/findall").retrieve()
				.onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new RuntimeException("API not found")))
				.onStatus(HttpStatus::is5xxServerError,
						error -> Mono.error(new NoEmployeesFoundException("No Employees found")))
				.bodyToFlux(Employee.class);

	}

	public Flux<Employee> fallbackForfindAll(Throwable e) {
		logger.log(Level.INFO, () -> "From fallbackForfindAll method" + e.getLocalizedMessage());
		Flux<Employee> f = Flux.error(new ServerException("Server Exception" + e.getLocalizedMessage()));

		return f;
	}

}
