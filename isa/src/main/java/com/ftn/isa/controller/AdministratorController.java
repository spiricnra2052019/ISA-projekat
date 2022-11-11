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
	public ResponseEntity<Administrator> createUser(@RequestBody Administrator administrator){
		Administrator savedUser = null;
		try {
			savedUser = administratorService.save(administrator);
			return new ResponseEntity<Administrator>(savedUser, HttpStatus.CREATED);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Administrator>(savedUser, HttpStatus.CONFLICT);
		}
	}
	
}
