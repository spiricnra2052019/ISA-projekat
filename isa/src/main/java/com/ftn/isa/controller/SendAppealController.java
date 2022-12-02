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

import com.ftn.isa.model.BloodCenterAdministrator;
import com.ftn.isa.model.ScheduleCalendar;
import com.ftn.isa.model.SendAppeal;
import com.ftn.isa.service.SendAppealService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/send-appeal")
public class SendAppealController {
	@Autowired
	private SendAppealService sendAppealService;
	
	@Operation(summary = "Get all appeal's", description = "Get all appeal's", method="GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation",
					     content = @Content(mediaType = "application/json", 
					     array = @ArraySchema(schema = @Schema(implementation = SendAppeal.class))))
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<SendAppeal>> getAllTermins() {
		Collection<SendAppeal> schedules = sendAppealService.findAll();
		return new ResponseEntity<Collection<SendAppeal>>(schedules, HttpStatus.OK);
	}
	
	@Operation(summary = "Create new Appeal", description = "Create new Appeal", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created",
					content = { @Content(mediaType = "application/json", schema = @Schema(implementation = SendAppeal.class)) }),
			@ApiResponse(responseCode = "409", description = "Not possible to create new Appeal when given id is not null",
					content = @Content)
	})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SendAppeal> createNewAppeal(@RequestBody SendAppeal appeal){
		SendAppeal savedAppeal = null;
		try {
			savedAppeal = sendAppealService.save(appeal);
			return new ResponseEntity<SendAppeal>(savedAppeal, HttpStatus.CREATED);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<SendAppeal>(savedAppeal, HttpStatus.CONFLICT);
		}
	}

}
