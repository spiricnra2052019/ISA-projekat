package com.ftn.isa.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.isa.model.BloodCenter;
import com.ftn.isa.model.RegisteredUser;
import com.ftn.isa.service.BloodCenterService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/blood-centers")
public class BloodCenterController {
	
	@Autowired
	private BloodCenterService bloodCenterService;
	
	@Operation(summary = "Get all centers", description = "Get all centers", method="GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation",
					     content = @Content(mediaType = "application/json", 
					     array = @ArraySchema(schema = @Schema(implementation = BloodCenter.class))))
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<BloodCenter>> getBloodCenters() {
		Collection<BloodCenter> bloodCenters= bloodCenterService.findAll();
		return new ResponseEntity<Collection<BloodCenter>>(bloodCenters, HttpStatus.OK);
	}
	

	@GetMapping("/search")
	public ResponseEntity<List<BloodCenter>> searchUsers(@RequestParam("query") String query){
		return ResponseEntity.ok(bloodCenterService.searchCenters(query));
	}
	
	@GetMapping("/filter")
	public ResponseEntity<List<BloodCenter>> filterUsers(@RequestParam("searchQuery") String searchQuery, @RequestParam("filterQuery") float filterQuery){
		return ResponseEntity.ok(bloodCenterService.filterCenters(searchQuery, filterQuery));
	}
	
}
