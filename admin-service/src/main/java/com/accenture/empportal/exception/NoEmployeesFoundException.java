package com.accenture.empportal.exception;

public class NoEmployeesFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -695486056270031436L;

	public NoEmployeesFoundException(String Msg) {
		super(Msg);
	}
}
