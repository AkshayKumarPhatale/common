/**
 * 
 */
package com.accenture.empportal.entity;

/**
 * @author HANUMAN
 *
 */


public class Employee {

	
	private Long empId;

	private String empName;

	
	private String empGender;

	
	private String createdAt;

	private String updatedAt;

	
	private String empRole;

	private Double empSalary;
	
	
	
	private String joinedDate;

	private String manager;
	
	private String deptId;

	private  Department dept;
	
	public Employee() {
		
	}
	  
	

	public Employee(Long empId, String empName, String empGender, String createdAt, String updatedAt, String empRole,
			Double empSalary, String joinedDate, String manager, String deptId, Department dept) {
		
		this.empId = empId;
		this.empName = empName;
		this.empGender = empGender;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.empRole = empRole;
		this.empSalary = empSalary;
		this.joinedDate = joinedDate;
		this.manager = manager;
		this.deptId = deptId;
		this.dept = dept;
	}



	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
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

	public String getEmpGender() {
		return empGender;
	}

	public void setEmpGender(String empGender) {
		this.empGender = empGender;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getEmpRole() {
		return empRole;
	}

	public void setEmpRole(String empRole) {
		this.empRole = empRole;
	}

	public Double getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(Double empSalary) {
		this.empSalary = empSalary;
	}

	public String getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(String joinedDate) {
		this.joinedDate = joinedDate;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}



	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empGender=" + empGender + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + ", empRole=" + empRole + ", empSalary=" + empSalary
				+ ", joinedDate=" + joinedDate + ", manager=" + manager + ", deptId=" + deptId + ", dept=" + dept + "]";
	}
	
	
	
	
	
}
