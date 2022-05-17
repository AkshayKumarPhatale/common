package com.accenture.empportal.service;

import java.util.Optional;

import com.accenture.empportal.entity.Department;
import com.accenture.empportal.request.DepartmentRequest;

public interface IDepartmentService {
	public Optional<Department>  addDepartment(DepartmentRequest departmentRequest);
	public Optional<Department>  updateDepartment(DepartmentRequest departmentRequest);
	public Optional<Department>  findByDeptId(Long id);
	public Boolean existsBydeptName(String name);
	public Boolean existsBydeptId(Long deptId);
	
}
