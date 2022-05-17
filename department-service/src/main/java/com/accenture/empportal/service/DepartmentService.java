package com.accenture.empportal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.empportal.entity.Department;
import com.accenture.empportal.repository.DepartmentRepository;
import com.accenture.empportal.request.DepartmentRequest;

@Service
public class DepartmentService implements IDepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public Optional<Department> addDepartment(DepartmentRequest departmentRequest) {
		Boolean isDeptExists = existsBydeptName(departmentRequest.getDeptName());
		Department department = new Department();
		if (!isDeptExists) {
			
			department.setDeptId(departmentRequest.getDeptId());
			department.setCreatedAt(departmentRequest.getCreatedAt());
			department.setUpdatedAt(departmentRequest.getUpdatedAt());
			department.setLocation(departmentRequest.getLocation());
			department.setDeptName(departmentRequest.getDeptName());

			return Optional.ofNullable(departmentRepository.save(department));

		} else {
			return Optional.ofNullable(null);
		}
	}

	@Override
	public Optional<Department> updateDepartment(DepartmentRequest departmentRequest) {
		Department department = findByDeptId(departmentRequest.getDeptId()).orElse(null);
		if(department==null) {
			
		return	Optional.ofNullable(null);
		}
        department.setLocation(departmentRequest.getLocation());
        department.setUpdatedAt(departmentRequest.getUpdatedAt());
		return Optional.ofNullable(departmentRepository.save(department));
		
		
		
	}

	@Override
	public Optional<Department> findByDeptId(Long id) {
		
		return departmentRepository.findById(id);
	}

	@Override
	public Boolean existsBydeptName(String name) {
		
		return departmentRepository.existsBydeptName(name);
	}

	@Override
	public Boolean existsBydeptId(Long deptId) {
		
		return null;
	}

}
