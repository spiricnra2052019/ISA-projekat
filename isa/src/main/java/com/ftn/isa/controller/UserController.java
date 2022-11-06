package com.ftn.isa.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.isa.model.User;
import com.ftn.isa.service.UserService;

	

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Operation(summary = "Get all users", description = "Get all users", method="GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation",
					     content = @Content(mediaType = "application/json", 
					     array = @ArraySchema(schema = @Schema(implementation = User.class))))
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<User>> getUsers() {
		Collection<User> users = userService.findAll();
		return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
	}

}
