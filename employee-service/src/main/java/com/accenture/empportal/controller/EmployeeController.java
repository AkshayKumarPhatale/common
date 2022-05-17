package com.accenture.empportal.controller;

import com.accenture.empportal.entity.Employee;
import com.accenture.empportal.exception.EmployeeCreationExpection;
import com.accenture.empportal.exception.EmployeeNotFoundException;
import com.accenture.empportal.exception.EmployeeUpdationFailed;
import com.accenture.empportal.exception.NoEmployeesFoundException;
import com.accenture.empportal.request.EmployeeRequest;
import com.accenture.empportal.response.EmployeeResponse;
import com.accenture.empportal.service.IEmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/api/employee")
@RestController
@Api(value = "Employee API", tags = { "Employee Controller" })
public class EmployeeController {

	@Autowired
	private IEmployeeService iEmployeeService;

	@PostMapping(value = "/addEmployee")
	@ApiOperation(value = "save an employee", notes = "This method is for saving employee object to the db")
	public ResponseEntity<EmployeeResponse> addEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {

		String successMessage = "New Employee Created ";
		String failedMessage = "Employee Creation Failed";

		Employee savedEmployee = iEmployeeService.addEmployee(employeeRequest).get();

		if (savedEmployee.getEmpId() != null) {

			EmployeeRequest empDto = new EmployeeRequest();
			empDto.setCreatedAt(savedEmployee.getCreatedAt());
			empDto.setEmpName(savedEmployee.getEmpName());
			empDto.setEmpId(savedEmployee.getEmpId());
			empDto.setEmpGender(savedEmployee.getEmpGender().toString());
			empDto.setEmpRole(savedEmployee.getEmpRole().toString());
			empDto.setEmpSalary(savedEmployee.getEmpSalary());
			empDto.setUpdatedAt(savedEmployee.getUpdatedAt());
			empDto.setEmpId(savedEmployee.getEmpId());
			empDto.setDeptId(savedEmployee.getDeptId());
			empDto.setJoinedDate(savedEmployee.getJoinedDate());
			empDto.setManager(savedEmployee.getManager());
			EmployeeResponse employeeResponse = new EmployeeResponse(successMessage, true, empDto);

			return new ResponseEntity<EmployeeResponse>(employeeResponse, HttpStatus.CREATED);
		}

		else {
			throw new EmployeeCreationExpection(failedMessage);
		}

	}

	@PutMapping(value = "/updateEmployee")
	@ApiOperation(value = "save an employee", notes = "This method is for saving employee object to the db")
	public ResponseEntity<EmployeeResponse> updateEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {

		String successMessage = "Employee details updated ";
		String failedMessage = "Employee details update Failed";

		Employee savedEmployee = iEmployeeService.updateEmployee(employeeRequest).get();

		if (savedEmployee.getEmpId() != null) {

			EmployeeRequest empDto = new EmployeeRequest();
			empDto.setEmpName(savedEmployee.getEmpName());
			empDto.setEmpId(savedEmployee.getEmpId());
			empDto.setEmpGender(savedEmployee.getEmpGender().toString());
			empDto.setEmpRole(savedEmployee.getEmpRole().toString());
			empDto.setEmpSalary(savedEmployee.getEmpSalary());
			empDto.setUpdatedAt(savedEmployee.getUpdatedAt());
			empDto.setEmpId(savedEmployee.getEmpId());
			empDto.setDeptId(savedEmployee.getDeptId());
			empDto.setJoinedDate(savedEmployee.getJoinedDate());
			empDto.setManager(savedEmployee.getManager());

			EmployeeResponse employeeResponse = new EmployeeResponse(successMessage, true, empDto);

			return new ResponseEntity<EmployeeResponse>(employeeResponse, HttpStatus.CREATED);

		} else {
			throw new EmployeeUpdationFailed(failedMessage);
		}

	}

	@GetMapping("/{id}")
	@ApiOperation(value = "save an employee", notes = "This method is for saving employee object to the db")
	public ResponseEntity<Employee> findByEmpId(@NotNull @PathVariable(value = "id") Long empId) {

		Optional<Employee> emp = iEmployeeService.findByEmpId(empId);

		if (emp.isPresent()) {

			return new ResponseEntity<Employee>(emp.get(), HttpStatus.OK);
		} else {
			throw new EmployeeNotFoundException("Employee not found for id:" + "" + empId);
		}

	}

	@GetMapping("/findall")
	@ApiOperation(value = "save an employee", notes = "This method is for saving employee object to the db")
	public ResponseEntity<List<Employee>> findAll() {
		List<Employee> listEmployees = iEmployeeService.findAllEmployees();

		if (!listEmployees.isEmpty()) {

			return new ResponseEntity<List<Employee>>(listEmployees, HttpStatus.OK);
		} else {
			throw new NoEmployeesFoundException("No Employee Data Found");

		}

	}

}
