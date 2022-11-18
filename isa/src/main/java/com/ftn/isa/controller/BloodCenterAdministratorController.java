package com.ftn.isa.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.isa.model.Administrator;
import com.ftn.isa.model.BloodCenterAdministrator;
import com.ftn.isa.service.BloodCenterAdministratorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/blood-center-administrators")
public class BloodCenterAdministratorController {
	
	@Autowired
	private BloodCenterAdministratorService bloodCenterAdministratorService;
		
	@Operation(summary = "Get all center admins", description = "Get all center admins", method="GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation",
					     content = @Content(mediaType = "application/json", 
					     array = @ArraySchema(schema = @Schema(implementation = BloodCenterAdministrator.class))))
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<BloodCenterAdministrator>> getAdministrators() {
		Collection<BloodCenterAdministrator> admins = bloodCenterAdministratorService.findAll();
		return new ResponseEntity<Collection<BloodCenterAdministrator>>(admins, HttpStatus.OK);
	}
	
	@Operation(summary = "Create new BloodCenterAdministrator", description = "Create new BloodCenterAdministrator", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created",
					content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BloodCenterAdministrator.class)) }),
			@ApiResponse(responseCode = "409", description = "Not possible to create new Administrator when given id is not null",
					content = @Content)
	})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BloodCenterAdministrator> createBloodCenterAdministrator(@RequestBody BloodCenterAdministrator administrator){
		BloodCenterAdministrator savedAdministrator = null;
		try {
			savedAdministrator = bloodCenterAdministratorService.save(administrator);
			return new ResponseEntity<BloodCenterAdministrator>(savedAdministrator, HttpStatus.CREATED);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<BloodCenterAdministrator>(savedAdministrator, HttpStatus.CONFLICT);
		}
	}
	

}
