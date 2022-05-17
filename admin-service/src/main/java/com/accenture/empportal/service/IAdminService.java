package com.accenture.empportal.service;

import com.accenture.empportal.entity.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAdminService {
	
	 public Mono<Employee>   findEmpById(Long id);
	 public Flux<Employee> findAll();

}
