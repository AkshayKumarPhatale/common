package com.accenture.empportal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.empportal.entity.Employee;
import com.accenture.empportal.entity.Gender;
import com.accenture.empportal.entity.Role;
import com.accenture.empportal.repository.EmployeeRepository;
import com.accenture.empportal.request.EmployeeRequest;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Optional<Employee> addEmployee(EmployeeRequest empRequest) {
		Employee employee = new Employee();

		Gender gender = Gender.valueOf(empRequest.getEmpGender());
		employee.setEmpGender(gender);
		employee.setEmpName(empRequest.getEmpName());
		employee.setEmpSalary(empRequest.getEmpSalary());
		employee.setCreatedAt(empRequest.getCreatedAt());
		employee.setUpdatedAt(empRequest.getUpdatedAt());
		Role role = Role.valueOf(empRequest.getEmpRole());
		employee.setEmpRole(role);
		
		employee.setJoinedDate(empRequest.getJoinedDate());
		employee.setDeptId(empRequest.getDeptId());
		employee.setManager(empRequest.getManager());
		return Optional.ofNullable(employeeRepository.save(employee));

	}

	@Override
	public Optional<Employee> updateEmployee(EmployeeRequest empRequest) {

		Employee employee = new Employee();
		Gender gender = Gender.valueOf(empRequest.getEmpGender());
		employee.setEmpId(empRequest.getEmpId());
		employee.setEmpGender(gender);
		employee.setEmpName(empRequest.getEmpName());
		employee.setEmpSalary(empRequest.getEmpSalary());
		employee.setCreatedAt(empRequest.getCreatedAt());
		employee.setUpdatedAt(empRequest.getUpdatedAt());
		Role role = Role.valueOf(empRequest.getEmpRole());
		employee.setEmpRole(role);
		employee.setJoinedDate(empRequest.getJoinedDate());
		employee.setDeptId(empRequest.getDeptId());
		employee.setManager(empRequest.getManager());
		return Optional.ofNullable(employeeRepository.save(employee));

	}

	@Override
	public Optional<Employee> findByEmpId(Long id) {

		return employeeRepository.findByEmpId(id);
	}

	@Override
	public List<Employee> findAllEmployees() {

		return employeeRepository.findAll();

	}

	

}
