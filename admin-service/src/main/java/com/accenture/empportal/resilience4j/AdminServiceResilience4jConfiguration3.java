package com.accenture.empportal.resilience4j;

import java.util.concurrent.CompletableFuture;
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
import com.accenture.empportal.exception.ServerException;

import reactor.core.publisher.Mono;

@Component
public class AdminServiceResilience4jConfiguration3 {

	@Autowired
	private WebClient webClient;

	// private static final ExecutorService executorService =
	// Executors.newCachedThreadPool();

	private static final Logger logger = Logger.getLogger(AdminServiceResilience4jConfiguration3.class.getName());

	Employee employee = new Employee();
	Department department = new Department();
	
	public Mono<Employee> findByEmpId(Long id)  {
		
		
		
   return CompletableFuture.supplyAsync(()-> {

			
	   Mono<Employee> emp= webClient
						.get()
						.uri("http://127.0.0.1:2020/api/employee/" + id).retrieve()
						.onStatus(HttpStatus::is5xxServerError,
								error -> Mono.error(new EmployeeNotFoundException("Employee not Found with this id")))
						.bodyToMono(Employee.class);
				return emp;
				
			})
		   
		   
		   .thenApplyAsync(
				
			(employeedto) -> {
				Employee emp=employeedto.block();
				BeanUtils.copyProperties(emp, employee);
				Department dept = webClient.get()
						.uri("http://127.0.0.1:2121/api/department/" + emp.getDeptId()).retrieve()
						.onStatus(HttpStatus::is5xxServerError,
								error -> Mono.error(new DepartmentNotFoundException("Department not Found with this id")))
						.bodyToMono(Department.class).block();
				
				BeanUtils.copyProperties(dept, department);
				employee.setDept(department);
				return Mono.just(employee);
				
	}).join();

		
		
		
	
		
	}
	
	


}
