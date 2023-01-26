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

import com.ftn.isa.enums.Role;
import com.ftn.isa.model.Employee;
import com.ftn.isa.model.PatientQuestion;
import com.ftn.isa.model.RegisteredUser;
import com.ftn.isa.service.PatientQuestionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/query")
public class PatientQuestionController {
	@Autowired
	private PatientQuestionService patientQuestionService;
	
	@Operation(summary = "Get all questions", description = "Get all questions", method="GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation",
					     content = @Content(mediaType = "application/json", 
					     array = @ArraySchema(schema = @Schema(implementation = Employee.class))))
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<PatientQuestion>> getEmployers() {
		Collection<PatientQuestion> patientQuery = patientQuestionService.findAll();
		return new ResponseEntity<Collection<PatientQuestion>>(patientQuery, HttpStatus.OK);
	}
}
