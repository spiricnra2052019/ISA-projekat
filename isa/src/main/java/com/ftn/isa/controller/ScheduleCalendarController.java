package com.ftn.isa.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.isa.model.BloodCenterAdministrator;
import com.ftn.isa.model.ScheduleCalendar;
import com.ftn.isa.service.ScheduleCalendarService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/schedule-calendar")
public class ScheduleCalendarController {
	@Autowired
	private ScheduleCalendarService scheduleCalendarService;
	
	@Operation(summary = "Get all schedule's", description = "Get all schedule's", method="GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation",
					     content = @Content(mediaType = "application/json", 
					     array = @ArraySchema(schema = @Schema(implementation = ScheduleCalendar.class))))
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<ScheduleCalendar>> getAllTermins() {
		Collection<ScheduleCalendar> schedules = scheduleCalendarService.findAll();
		return new ResponseEntity<Collection<ScheduleCalendar>>(schedules, HttpStatus.OK);
	}
}
