package com.ftn.isa.controller;

import com.ftn.isa.dto.QueryAnswerDTO;
import com.ftn.isa.model.Employee;
import com.ftn.isa.model.PatientAnswer;
import com.ftn.isa.service.PatientAnswerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/query-answer")
public class PatientAnswerController {
    @Autowired
    private PatientAnswerService patientAnswerService;

    @Operation(summary = "Save answer", description = "Save answer", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Employee.class))))
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientAnswer> saveAnswer(@RequestBody QueryAnswerDTO queryAnswerDTO) {
        PatientAnswer savedPatientAnswer = null;
        try {
            savedPatientAnswer = patientAnswerService.save(queryAnswerDTO);
            return new ResponseEntity<PatientAnswer>(savedPatientAnswer, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<PatientAnswer>(savedPatientAnswer, HttpStatus.CONFLICT);
        }
    }

    @Operation(summary = "Check if patient has already answered", description = "Check if patient has already answered", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json"))
    })
    @GetMapping(value = "/check/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> checkIfPatientHasAlreadyAnswered(
            @Parameter(name = "id", description = "ID of user", required = true) @PathVariable("id") Long id) {
        Boolean hasAnswered = null;
        try {
            hasAnswered = patientAnswerService.checkIfPatientHasAlreadyAnswered(id);
            return new ResponseEntity<Boolean>(hasAnswered, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Boolean>(hasAnswered, HttpStatus.CONFLICT);
        }
    }
}
