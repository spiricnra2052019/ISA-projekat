package com.ftn.isa.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.isa.dto.ScheduleCalendarDTO;
import com.ftn.isa.dto.UserAppointmentDTO;
import com.ftn.isa.model.BloodCenterAdministrator;
import com.ftn.isa.model.RegisteredUser;
import com.ftn.isa.model.ScheduleCalendar;
import com.ftn.isa.service.ScheduleCalendarService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/schedule-calendar")
public class ScheduleCalendarController {
	@Autowired
	private ScheduleCalendarService scheduleCalendarService;

	@Operation(summary = "Get all schedule's", description = "Get all schedule's", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ScheduleCalendar.class))))
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<ScheduleCalendar>> getAllTermins() {
		Collection<ScheduleCalendar> schedules = scheduleCalendarService.findAll();
		return new ResponseEntity<Collection<ScheduleCalendar>>(schedules, HttpStatus.OK);
	}

	@Operation(summary = "Create new Schedule Appointment", description = "Create new Schedule Appointment", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ScheduleCalendar.class)) }),
			@ApiResponse(responseCode = "409", description = "Not possible to create new RegisteredUser when given id is not null", content = @Content)
	})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ScheduleCalendar> scheduleAppointment(@RequestBody ScheduleCalendarDTO scheduleCalendar) {
		try {
			ScheduleCalendar schedule = scheduleCalendarService.save(scheduleCalendar);
			return new ResponseEntity<ScheduleCalendar>(schedule, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(summary = "Get all schedules's for blood center administrator", description = "Get all schedules's for blood center administrator", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ScheduleCalendar.class))))
	})
	@GetMapping(value = "/blood-center/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<ScheduleCalendar>> getAllTerminsForBloodCenter(@PathVariable("id") Long id) {
		Collection<ScheduleCalendar> schedules = scheduleCalendarService.findAllByBloodCenterId(id);
		return new ResponseEntity<Collection<ScheduleCalendar>>(schedules, HttpStatus.OK);
	}

	@Operation(summary = "Get all schedule's for user", description = "Get all schedule's for user", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ScheduleCalendar.class))))
	})
	@GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<ScheduleCalendar>> getAllTerminsForUser(@PathVariable("id") Long id) {
		Collection<ScheduleCalendar> schedules = scheduleCalendarService.findAllByUserId(id);
		return new ResponseEntity<Collection<ScheduleCalendar>>(schedules, HttpStatus.OK);
	}

	@Operation(summary = "Schedule appointment for user", description = "Schedule appointment for user", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ScheduleCalendar.class)) }),
			@ApiResponse(responseCode = "409", description = "Not possible to create new RegisteredUser when given id is not null", content = @Content)
	})
	@PostMapping(value = "/appointment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ScheduleCalendar> scheduleAppointmentForUser(
			@RequestBody UserAppointmentDTO userAppointmentDTO) {
		try {
			ScheduleCalendar schedule = scheduleCalendarService.scheduleAppointmentForUser(
					userAppointmentDTO.getAppointmentId(), userAppointmentDTO.getUser().getId());
			return new ResponseEntity<ScheduleCalendar>(schedule, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(summary = "Decline appointment for user", description = "Decline appointment for user", method = "PUT")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ScheduleCalendar.class))))
	})
	@PutMapping(value = "/decline/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ScheduleCalendar> declineAppointment(@PathVariable("id") Long id) {
		try {
			ScheduleCalendar schedule = scheduleCalendarService.declineAppointment(id);
			return new ResponseEntity<ScheduleCalendar>(schedule, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
