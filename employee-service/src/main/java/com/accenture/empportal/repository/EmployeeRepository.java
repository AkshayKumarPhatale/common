package com.accenture.empportal.repository;

import com.accenture.empportal.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	
	public Optional<Employee> findByEmpId(Long id);
}
