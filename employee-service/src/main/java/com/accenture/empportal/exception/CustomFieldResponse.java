package com.accenture.empportal.exception;

import java.util.List;

public class CustomFieldResponse {
	private String message;
	private List<CustomField> details;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<CustomField> getDetails() {
		return details;
	}
	public void setDetails(List<CustomField> details) {
		this.details = details;
	}
	
	

}
