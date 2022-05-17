package com.accenture.empportal.resilience4j;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.accenture.empportal.entity.Department;
import com.accenture.empportal.entity.Employee;
import com.accenture.empportal.exception.EmployeeNotFoundException;
import com.accenture.empportal.exception.ServerException;

import reactor.core.publisher.Mono;

@Component
public class AdminServiceResilience4jConfiguration {

	@Autowired
	private WebClient webClient;

	// private static final ExecutorService executorService =
	// Executors.newCachedThreadPool();

	private static final Logger logger = Logger.getLogger(AdminServiceResilience4jConfiguration.class.getName());

	Employee employee = new Employee();
	Department department = new Department();

	//@CircuitBreaker(name = "findByEmpIdCircuitBreaker", fallbackMethod = "fallbackForFindByEmpId")
	//@RateLimiter(name = "findByEmpIdRateLimiter", fallbackMethod = "fallbackForRateLimiter")
	public Mono<Employee> findByEmpId(Long id) {

		CompletableFuture.supplyAsync(() -> {

			logger.log(Level.INFO, () -> "id" + id);

			Mono<Employee> emp = webClient
					.get()
					.uri("http://localhost:2020/api/employee/" + id).retrieve()
					.onStatus(HttpStatus::is5xxServerError,
							error -> Mono.error(new EmployeeNotFoundException("Employee not Found with this id")))
					.bodyToMono(Employee.class);
					

			logger.log(Level.INFO, () -> "Response from webclient emp{}" + emp);
			BeanUtils.copyProperties(emp, employee);
			logger.log(Level.INFO, () -> "Response after bean copy employee{}" + employee);
			return emp.block();
		})
			  .whenComplete((result, ex) -> { if (result.getEmpId() == null) {
			  logger.log(Level.INFO, () -> "exception was thrown:" +
			  ex.getLocalizedMessage()); throw new
			  ServerException("server exception invoked"); } })
			 

				.thenApply((employee) -> {
					logger.log(Level.INFO, () -> "called from ApplyAsync employee{}" + employee);
					Department dept = webClient.get()
							.uri("http://localhost:2121/api/department/" + employee.getDeptId()).retrieve()
							.bodyToMono(Department.class).block();
					logger.log(Level.INFO, () -> "Response from webclient ApplyAsync-1 dept{}" + department);
					BeanUtils.copyProperties(dept, department);
					return dept;
				})
					  .whenComplete((result, ex) -> { if (result.getDeptId() == null) {
					  logger.log(Level.INFO, () -> "exception was throw" +
					  ex.getLocalizedMessage()); throw new ServerException("server exception"); }
					  }).join();
					 
		;

		employee.setDept(department);
		logger.log(Level.INFO, () -> "inside completable future");
		return Mono.just(employee);

	}
/*
	public Mono<Employee> fallbackForFindByEmpId(Exception e) {
		logger.log(Level.INFO, () -> "inside fallbackForFindByEmpId" + e.getLocalizedMessage());
		return Mono.error(new ServerException("Server Exception"));
	}

	@CircuitBreaker(name = "findAllCircuitBreaker", fallbackMethod = "fallbackForfindAll")
	public Flux<Employee> findAll() {
		logger.log(Level.INFO, () -> "From findAll method");
		return webClient.get().uri("http://localhost:2020/api/employee/findall").retrieve()
				.onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new RuntimeException("API not found")))
				.onStatus(HttpStatus::is5xxServerError,
						error -> Mono.error(new NoEmployeeFoundException("No Employees found")))
				.bodyToFlux(Employee.class);

	}

	public Flux<Employee> fallbackForfindAll(Throwable e) {
		logger.log(Level.INFO, () -> "From fallbackForfindAll method" + e.getLocalizedMessage());
		Flux<Employee> f = Flux.error(new ServerException("Server Exception" + e.getLocalizedMessage()));

		return f;
	}

	/*
	 * public Mono<Employee> fallbackForRateLimiter(Throwable e) {
	 * logger.log(Level.INFO, () -> "From rateLimiterfallback method" +
	 * e.getLocalizedMessage()); return Mono.error(new
	 * ServerException("Server Exception" + e.getLocalizedMessage())); }
	 */

}
