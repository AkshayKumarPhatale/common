package com.accenture.empportal.response;

import com.accenture.empportal.request.EmployeeRequest;

public class EmployeeResponse {
	private String message;
	private Boolean status;
	private  EmployeeRequest employeeRequest;
	
	public EmployeeResponse() {
		
	}
	
	public EmployeeResponse(String message, Boolean status, EmployeeRequest employeeRequest) {
		
		this.message = message;
		this.status = status;
		this.employeeRequest = employeeRequest;
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
	public EmployeeRequest getEmployeeRequest() {
		return employeeRequest;
	}
	public void setEmployeeRequest(EmployeeRequest employeeRequest) {
		this.employeeRequest = employeeRequest;
	}

	@Override
	public String toString() {
		return "EmployeeResponse [message=" + message + ", status=" + status + ", employeeRequest=" + employeeRequest
				+ "]";
	}
	
	
	

}
