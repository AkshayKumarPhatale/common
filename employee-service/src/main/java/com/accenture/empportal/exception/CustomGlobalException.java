package com.accenture.empportal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CustomGlobalException {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	
	public final ResponseEntity<CustomFieldResponse> processValidationError(MethodArgumentNotValidException ex) {
		
		final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

		final List<CustomField> customFieldErrors = new ArrayList<>();
		 CustomFieldResponse customFieldResponse = new CustomFieldResponse();
		for (FieldError fieldError : fieldErrors) {

			final String field = fieldError.getField();

			final String message = fieldError.getDefaultMessage();

			CustomField customField =  new CustomField();
			customField.setMessage(message);
			customField.setField(field);
			customFieldErrors.add(customField);
			
			 customFieldResponse.setMessage("failed to save");
			 customFieldResponse.setDetails(customFieldErrors);
		}

		return new ResponseEntity<>(customFieldResponse, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(EmployeeCreationExpection.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public final ResponseEntity<ErrorResponse> handleUserLoanCreationException(EmployeeCreationExpection ex) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("failed to save", details);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(EmployeeUpdationFailed.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public final ResponseEntity<ErrorResponse> handleEmployeeUpdationException(EmployeeUpdationFailed ex) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("failed to Update", details);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public final ResponseEntity<ErrorResponse> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Employee Not found", details);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NoEmployeesFoundException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public final ResponseEntity<ErrorResponse> handleNoEmployeeFoundException(NoEmployeesFoundException ex) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("No Employee Data", details);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
