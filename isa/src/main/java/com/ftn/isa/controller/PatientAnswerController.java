package com.ftn.isa.controller;

import com.ftn.isa.model.Employee;
import com.ftn.isa.model.PatientAnswer;
import com.ftn.isa.service.PatientAnswerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/query-answer")
public class PatientAnswerController {
    @Autowired
    private PatientAnswerService patientAnswerService;

    @Operation(summary = "Save answer", description = "Save answer", method="POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Employee.class))))
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientAnswer> saveAnswer(@RequestBody PatientAnswer patientAnswer) {
        PatientAnswer savedPatientAnswer = null;
        try{
            savedPatientAnswer = patientAnswerService.save(patientAnswer);
            return new ResponseEntity<PatientAnswer>(savedPatientAnswer, HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<PatientAnswer>(savedPatientAnswer, HttpStatus.CONFLICT);
        }
    }
}
