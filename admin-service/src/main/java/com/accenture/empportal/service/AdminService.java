package com.accenture.empportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.accenture.empportal.entity.Employee;
import com.accenture.empportal.resilience4j.AdminServiceResilience4j;
import com.accenture.empportal.resilience4j.AdminServiceResilience4jConfiguration;
import com.accenture.empportal.resilience4j.AdminServiceResilience4jConfiguration2;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AdminService implements IAdminService {
	
	  @Autowired
	  private AdminServiceResilience4jConfiguration2 adminServiceResilience4j;
	
	
	
	
	//  @Autowired private AdminServiceResilience4j adminServiceResilience4j;
	 
	/* Multiple service call */
	@Override
	public Mono<Employee> findEmpById(Long id) {
		return adminServiceResilience4j.findByEmpId(id);
		
	}

	@Override
	public Flux<Employee> findAll() {
		System.out.println("find all service");
	//		return adminServiceResilience4j.findAll();
		return adminServiceResilience4j.findAll();
	}

}
