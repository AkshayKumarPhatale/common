package com.accenture.empportal.service;

import java.util.List;
import java.util.Optional;

import com.accenture.empportal.entity.Employee;
import com.accenture.empportal.request.EmployeeRequest;

public interface IEmployeeService {
	
	public Optional<Employee>  addEmployee(EmployeeRequest empRequest);
	public Optional<Employee>  updateEmployee(EmployeeRequest empRequest);
	public Optional<Employee> findByEmpId(Long id);
	//public Employee findByEmpId(Long id);
	public List<Employee> findAllEmployees();

}
