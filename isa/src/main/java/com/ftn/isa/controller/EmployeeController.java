package com.ftn.isa.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.isa.model.Employee;
import com.ftn.isa.model.RegisteredUser;
import com.ftn.isa.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/employers")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Operation(summary = "Get all employers", description = "Get all employers", method="GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation",
					     content = @Content(mediaType = "application/json", 
					     array = @ArraySchema(schema = @Schema(implementation = Employee.class))))
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Employee>> getEmployers() {
		Collection<Employee> employee = employeeService.findAll();
		return new ResponseEntity<Collection<Employee>>(employee, HttpStatus.OK);
	}
	
	@Operation(summary = "Create new Employee", description = "Create new Employee", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created",
					content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class)) }),
			@ApiResponse(responseCode = "409", description = "Not possible to create new Employee when given id is not null",
					content = @Content)
	})
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
		Employee savedEmployee = null;
		try {
			savedEmployee = employeeService.save(employee);
			return new ResponseEntity<Employee>(savedEmployee, HttpStatus.CREATED);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Employee>(savedEmployee, HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> editEmployee(@RequestBody Employee employee){
		Employee savedE = null;
		try {
			savedE = employeeService.edit(employee);
			return new ResponseEntity<Employee>(savedE, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Employee>(savedE, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getEmployeeById(@Parameter(name="id", description = "ID of user", required = true) @PathVariable("id") Long id) {
		Employee employee = employeeService.findOne(id);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	
}
