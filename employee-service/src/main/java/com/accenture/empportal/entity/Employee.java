/**
 * 
 */
package com.accenture.empportal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author HANUMAN
 *
 */

@Table(name = "EMPLOYEE")
@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long empId;

	private String empName;

	@Enumerated(EnumType.STRING)
	private Gender empGender;

	@CreationTimestamp
	private Date createdAt;

	@UpdateTimestamp
	private Date updatedAt;

	@Enumerated(EnumType.STRING)
	private Role empRole;

	private Double empSalary;
	
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate joinedDate;

	private String manager;

	private Long deptId;

	public Employee() {

	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Gender getEmpGender() {
		return empGender;
	}

	public void setEmpGender(Gender empGender) {
		this.empGender = empGender;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Role getEmpRole() {
		return empRole;
	}

	public void setEmpRole(Role empRole) {
		this.empRole = empRole;
	}

	public Double getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(Double empSalary) {
		this.empSalary = empSalary;
	}

	public LocalDate getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(LocalDate joinedDate) {
		this.joinedDate = joinedDate;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	
}
