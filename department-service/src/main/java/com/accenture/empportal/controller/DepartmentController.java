package com.accenture.empportal.controller;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.empportal.entity.Department;
import com.accenture.empportal.exception.DepartmentCreationException;
import com.accenture.empportal.exception.DepartmentNotFoundException;
import com.accenture.empportal.request.DepartmentRequest;
import com.accenture.empportal.response.DepartmentResponse;
import com.accenture.empportal.service.DepartmentService;
import com.accenture.empportal.service.IDepartmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/department")
@Api(value = "Department API", tags = { "Department Controller" })
public class DepartmentController {

	@Autowired
	private IDepartmentService departmentService;

	@PostMapping(value = "/addDepartment")
	@ApiOperation(value = "save department", notes = "This method is for saving department object to the db")
	public ResponseEntity<DepartmentResponse> addDepartment(@RequestBody DepartmentRequest departmentRequest) {

		String successMessage = "New Department Created ";
		String failedMessage = "Department Creation Failed";
		String alreadyExists = "Department already Exists";
		Optional<Department> department = departmentService.addDepartment(departmentRequest);
		DepartmentResponse departmentResponse = new DepartmentResponse();
		if (department.isPresent() && department.get().getDeptId() != null) {
			DepartmentRequest departmentRequestDto = new DepartmentRequest();
			departmentRequestDto.setCreatedAt(department.get().getCreatedAt());
			departmentRequestDto.setDeptId(department.get().getDeptId());
			departmentRequestDto.setDeptName(department.get().getDeptName());
			departmentRequestDto.setLocation(department.get().getLocation());
			departmentRequestDto.setUpdatedAt(department.get().getUpdatedAt());
			
			departmentResponse.setDepartmentRequest(departmentRequestDto);
			departmentResponse.setMessage(successMessage);
			departmentResponse.setStatus(true);
			return new ResponseEntity<DepartmentResponse>(departmentResponse, HttpStatus.CREATED);

		}

		else if (!department.isPresent())

		{

			// throw new DepartmentAlreadyExistsException(alreadyExists);

			departmentResponse.setMessage(alreadyExists);
			departmentResponse.setStatus(false);
			return new ResponseEntity<DepartmentResponse>(departmentResponse, HttpStatus.CONFLICT);
		} else {

			throw new DepartmentCreationException(failedMessage);

			/*
			 * departmentResponse.setMessage(failedMessage);
			 * departmentResponse.setStatus(false); return new
			 * ResponseEntity<DepartmentResponse>(departmentResponse,
			 * HttpStatus.INTERNAL_SERVER_ERROR);
			 */
		}

	}

	@ApiOperation(value = "update the department", notes = "This method is for update department object to the db")
	@PutMapping(value = "/updateDepartment")
	public ResponseEntity<DepartmentResponse> updateDepartment(@RequestBody DepartmentRequest departmentRequest) {
		String successMessage = " Department details updated  ";
		String failedMessage = "update failed";
		String notExists = "Department id doesn't Exists";
		Optional<Department> department = departmentService.updateDepartment(departmentRequest);

		DepartmentResponse departmentResponse = new DepartmentResponse();
		if (department.isPresent() && department.get().getDeptId() != null) {
			DepartmentRequest departmentRequestDto = new DepartmentRequest();
			departmentRequestDto.setCreatedAt(department.get().getCreatedAt());
			departmentRequestDto.setDeptId(department.get().getDeptId());
			departmentRequestDto.setDeptName(department.get().getDeptName());
			departmentRequestDto.setLocation(department.get().getLocation());
			departmentRequestDto.setUpdatedAt(department.get().getUpdatedAt());
			
			departmentResponse.setDepartmentRequest(departmentRequestDto);
			departmentResponse.setMessage(successMessage);
			departmentResponse.setStatus(true);
			return new ResponseEntity<DepartmentResponse>(departmentResponse, HttpStatus.CREATED);

		}

		else if (!department.isPresent())

		{

			// throw new DepartmentAlreadyExistsException(alreadyExists);

			departmentResponse.setMessage(notExists);
			departmentResponse.setStatus(false);
			return new ResponseEntity<DepartmentResponse>(departmentResponse, HttpStatus.CONFLICT);
		} else {

			throw new DepartmentCreationException(failedMessage);

			/*
			 * departmentResponse.setMessage(failedMessage);
			 * departmentResponse.setStatus(false); return new
			 * ResponseEntity<DepartmentResponse>(departmentResponse,
			 * HttpStatus.INTERNAL_SERVER_ERROR);
			 */
		}

	}
	
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Get department", notes = "This method is for get Department object from the db")
	public ResponseEntity<Department> findByEmpId(@NotNull @PathVariable(value = "id") Long id) {

		Optional<Department> deapartment = departmentService.findByDeptId(id);

		if (deapartment.isPresent()) {

			return new ResponseEntity<Department>(deapartment.get(), HttpStatus.OK);
		} else {
			throw new DepartmentNotFoundException("Employee not found for id:" + "" + id);
		}

	}


}
