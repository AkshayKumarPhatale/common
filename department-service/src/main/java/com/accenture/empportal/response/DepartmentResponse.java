package com.accenture.empportal.response;

import com.accenture.empportal.request.DepartmentRequest;

public class DepartmentResponse {
	private String message;
	private Boolean status;
	private  DepartmentRequest departmentRequest;
	
	public DepartmentResponse() {
		
	}
	
	
	public DepartmentResponse(String message, Boolean status, DepartmentRequest departmentRequest) {
		
		this.message = message;
		this.status = status;
		this.departmentRequest = departmentRequest;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public DepartmentRequest getDepartmentRequest() {
		return departmentRequest;
	}
	public void setDepartmentRequest(DepartmentRequest departmentRequest) {
		this.departmentRequest = departmentRequest;
	}
	
	

}
