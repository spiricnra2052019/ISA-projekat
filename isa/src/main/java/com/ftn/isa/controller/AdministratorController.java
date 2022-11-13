package com.ftn.isa.controller;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.ftn.isa.model.Administrator;
import com.ftn.isa.service.AdministratorService;

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
			savedAdministrator = administratorService.save(administrator);
			return new ResponseEntity<Administrator>(savedAdministrator, HttpStatus.CREATED);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Administrator>(savedAdministrator, HttpStatus.CONFLICT);
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
	
	/*
	@Operation(summary = "Create new Centers Administrators", description = "Create new Centers Administrators", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created",
					content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Administrator.class)) }),
			@ApiResponse(responseCode = "409", description = "Not possible to create new Centers Administrators when given id is not null",
					content = @Content)
	})
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CenterAdministrator> createCenterAdministrator(@RequestBody CenterAdministrator centerAdmin){
		CenterAdministrator savedCenterAdmin = null;
		try {
			savedCenterAdmin = administratorService.save(centerAdmin);
			return new ResponseEntity<CenterAdministrator>(savedCenterAdmin, HttpStatus.CREATED);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<CenterAdministrator>(savedCenterAdmin, HttpStatus.CONFLICT);
		}
	}
	*/
}
