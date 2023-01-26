package com.ftn.isa.controller;

import java.util.Collection;


import com.ftn.isa.model.RegisteredUser;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ftn.isa.model.Administrator;
import com.ftn.isa.model.Employee;
import com.ftn.isa.service.AdministratorService;
import com.ftn.isa.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/administrators")
public class AdministratorController {
	
	@Autowired
	private AdministratorService administratorService;
	private EmployeeService employeeService;
	
	@Operation(summary = "Get all admins", description = "Get all admins", method="GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation",
					     content = @Content(mediaType = "application/json", 
					     array = @ArraySchema(schema = @Schema(implementation = Administrator.class))))
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Administrator>> getAdministrators() {
		Collection<Administrator> admins = administratorService.findAll();
		return new ResponseEntity<Collection<Administrator>>(admins, HttpStatus.OK);
	}
	
	@Operation(summary = "Create new Administrators", description = "Create new Administrators", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created",
					content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Administrator.class)) }),
			@ApiResponse(responseCode = "409", description = "Not possible to create new Administrator when given id is not null",
					content = @Content)
	})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Administrator> createAdministrator(@RequestBody Administrator administrator){
		Administrator savedAdministrator = null;
		try {
			administrator.setValidated(false);
			savedAdministrator = administratorService.save(administrator);
			return new ResponseEntity<Administrator>(savedAdministrator, HttpStatus.CREATED);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Administrator>(savedAdministrator, HttpStatus.CONFLICT);
		}
	}

	@Operation(summary = "Get admin", description = "Get admin", method="GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK",
					content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Administrator.class)) }),
			@ApiResponse(responseCode = "400", description = "Not possible to get user.",
					content = @Content)
	})
	@GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Administrator> getAdminByUsername(@Parameter(name="username", description = "Username of user", required = true) @PathVariable("username") String username) {
		Administrator administrator = administratorService.findOneByUsername(username);
		System.out.println(administrator.getUsername());
		return new ResponseEntity<Administrator>(administrator, HttpStatus.OK);
	}

	@Operation(summary = "Edit Administrator", description = "Edit Administrator", method = "PUT")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Edited",
					content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Administrator.class)) }),
			@ApiResponse(responseCode = "400", description = "Bad request",
					content = @Content)
	})
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Administrator> editUser(@RequestBody Administrator administrator){
		Administrator savedAdministrator = null;
		try {
			savedAdministrator = administratorService.save(administrator);
			return new ResponseEntity<Administrator>(savedAdministrator, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Administrator>(savedAdministrator, HttpStatus.BAD_REQUEST);
		}
	}

	/*
	@Operation(summary = "Create new Centers", description = "Create new Centers", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created",
					content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Administrator.class)) }),
			@ApiResponse(responseCode = "409", description = "Not possible to create new Center when given id is not null",
					content = @Content)
	})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Center> createCenter(@RequestBody Center center){
		Center savedCenter = null;
		try {
			savedCenter = administratorService.save(center);
			return new ResponseEntity<Center>(savedCenter, HttpStatus.CREATED);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Center>(savedCenter, HttpStatus.CONFLICT);
		}
	}
	*/
	
}
