package com.ftn.isa.controller;


import com.ftn.isa.model.RegisteredUser;
import com.ftn.isa.model.SendAppeal;
import com.ftn.isa.model.UserVisitHistory;
import com.ftn.isa.service.UserVisitHistoryService;
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
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/employee-report")
public class UserVisitHistoryController {
    @Autowired
    private UserVisitHistoryService userVisitHistoryService;

    @Operation(summary = "Get all report's", description = "Get all report's", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserVisitHistory.class))))
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<UserVisitHistory>> getAllTermins() {
        Collection<UserVisitHistory> schedules = userVisitHistoryService.findAll();
        return new ResponseEntity<Collection<UserVisitHistory>>(schedules, HttpStatus.OK);
    }

    @Operation(summary = "Create new Report", description = "Create new Report", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserVisitHistory.class)) }),
            @ApiResponse(responseCode = "409", description = "Not possible to create new Report when given id is not null",
                    content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserVisitHistory> createNewReport(@RequestBody UserVisitHistory report){
        UserVisitHistory savedReport = null;
        try {
            savedReport = userVisitHistoryService.save(report);
            return new ResponseEntity<UserVisitHistory>(savedReport, HttpStatus.CREATED);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<UserVisitHistory>(savedReport, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserVisitHistory>> searchReports(@RequestParam("query") String query){
        return ResponseEntity.ok(userVisitHistoryService.searchReports(query));
    }
}
