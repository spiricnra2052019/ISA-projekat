package com.ftn.isa.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.isa.dto.UserLoginDTO;
import com.ftn.isa.model.RegisteredUser;
import com.ftn.isa.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

	

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Operation(summary = "Get all users", description = "Get all users", method="GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation",
					     content = @Content(mediaType = "application/json", 
					     array = @ArraySchema(schema = @Schema(implementation = RegisteredUser.class))))
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<RegisteredUser>> getUsers() {
		Collection<RegisteredUser> users = userService.findAll();
		return new ResponseEntity<Collection<RegisteredUser>>(users, HttpStatus.OK);
	}
	
	@Operation(summary = "Create new RegisteredUser", description = "Create new RegisteredUser", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created",
					content = { @Content(mediaType = "application/json", schema = @Schema(implementation = RegisteredUser.class)) }),
			@ApiResponse(responseCode = "409", description = "Not possible to create new RegisteredUser when given id is not null",
					content = @Content)
	})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RegisteredUser> createUser(@RequestBody RegisteredUser registeredUser){
		RegisteredUser savedUser = null;
		try {
			savedUser = userService.save(registeredUser);
			return new ResponseEntity<RegisteredUser>(savedUser, HttpStatus.CREATED);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<RegisteredUser>(savedUser, HttpStatus.CONFLICT);
		}
	}
	

	@GetMapping("/search")
	public ResponseEntity<List<RegisteredUser>> searchUsers(@RequestParam("query") String query){
		return ResponseEntity.ok(userService.searchUsers(query));
	
	@Operation(summary = "Login RegisteredUser", description = "Login RegisteredUser", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK",
					content = { @Content(mediaType = "application/json", schema = @Schema(implementation = RegisteredUser.class)) }),
			@ApiResponse(responseCode = "400", description = "Not possible to login user.",
					content = @Content)
	})
	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RegisteredUser> validateUser(@RequestBody UserLoginDTO userLoginDTO){
		RegisteredUser validUser = null;
		try {
			validUser = userService.loginUser(userLoginDTO.getEmail(), userLoginDTO.getPassword());
			return new ResponseEntity<RegisteredUser>(validUser, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<RegisteredUser>(validUser, HttpStatus.BAD_REQUEST);
		}
	}
	
}
