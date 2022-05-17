package com.accenture.empportal.exception;

public class EmployeeUpdationFailed extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public EmployeeUpdationFailed(String message) {
		super(message);
	}
}
