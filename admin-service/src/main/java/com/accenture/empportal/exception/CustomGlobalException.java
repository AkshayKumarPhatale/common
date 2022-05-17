package com.accenture.empportal.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
	
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public final ResponseEntity<ErrorResponse> handleUserLoanCreationException(EmployeeNotFoundException ex) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Employee Not Found", details);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(DepartmentNotFoundException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public final ResponseEntity<ErrorResponse> handleUserLoanCreationException(DepartmentNotFoundException ex) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("DepartmentNotFound", details);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(ServerException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public final ResponseEntity<ErrorResponse> handleServerException(ServerException ex) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("ServerException Please try later", details);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	

}
