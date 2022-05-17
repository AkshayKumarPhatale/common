package com.accenture.empportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.empportal.entity.Department;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	public boolean existsBydeptName(String name);
	public boolean existsBydeptId(Long deptId);
	
}
