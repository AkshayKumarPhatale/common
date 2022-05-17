package com.accenture.empportal.request;



import java.util.Date;


public class DepartmentRequest {

	
	private Long deptId;

	private String deptName;

	private String location;

	
	private Date createdAt;

	
	private Date updatedAt;

	public DepartmentRequest() {

	}

	public DepartmentRequest(Long deptId, String deptName, String location, Date createdAt, Date updatedAt) {

		this.deptId = deptId;
		this.deptName = deptName;
		this.location = location;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}

