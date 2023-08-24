package com.ftn.isa.controller;

import java.util.Collection;
import java.util.List;

import com.ftn.isa.dto.RegisterdUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.isa.auth.AuthService;
import com.ftn.isa.auth.AuthenticationResponse;
import com.ftn.isa.dto.UserLoginDTO;
import com.ftn.isa.enums.Role;
import com.ftn.isa.model.RegisteredUser;
import com.ftn.isa.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthService authService;

	@Operation(summary = "Get all users", description = "Get all users", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RegisteredUser.class))))
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<RegisteredUser>> getUsers() {
		Collection<RegisteredUser> users = userService.findAll();
		return new ResponseEntity<Collection<RegisteredUser>>(users, HttpStatus.OK);
	}

	@Operation(summary = "Create new RegisteredUser", description = "Create new RegisteredUser", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = RegisteredUser.class)) }),
			@ApiResponse(responseCode = "409", description = "Not possible to create new RegisteredUser when given id is not null", content = @Content)
	})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthenticationResponse> createUser(@RequestBody RegisteredUser registeredUser) {
		AuthenticationResponse savedUser = null;
		try {
			savedUser = authService.register(registeredUser);
			return new ResponseEntity<AuthenticationResponse>(savedUser, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<AuthenticationResponse>(savedUser, HttpStatus.CONFLICT);
		}
	}

	@Operation(summary = "Edit RegisteredUser", description = "Edit RegisteredUser", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Edited", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = RegisteredUser.class)) }),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
	})
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthenticationResponse> editUser(@RequestBody RegisteredUser user) {
		AuthenticationResponse editedUser = null;
		try {
			editedUser = authService.edit(user);
			return new ResponseEntity<AuthenticationResponse>(editedUser, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<AuthenticationResponse>(editedUser, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/search")
	public ResponseEntity<List<RegisteredUser>> searchUsers(@RequestParam("query") String query) {
		return ResponseEntity.ok(userService.searchUsers(query));
	}

	// @Operation(summary = "Login RegisteredUser", description = "Login
	// RegisteredUser", method = "POST")
	// @ApiResponses(value = {
	// @ApiResponse(responseCode = "200", description = "OK", content = {
	// @Content(mediaType = "application/json", schema = @Schema(implementation =
	// RegisteredUser.class)) }),
	// @ApiResponse(responseCode = "400", description = "Not possible to login
	// user.", content = @Content)
	// })
	// @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE,
	// produces = MediaType.APPLICATION_JSON_VALUE)
	// public ResponseEntity<RegisteredUser> validateUser(@RequestBody UserLoginDTO
	// userLoginDTO) {
	// RegisteredUser validUser = null;
	// try {
	// validUser = userService.loginUser(userLoginDTO.getEmail(),
	// userLoginDTO.getPassword());
	// return new ResponseEntity<RegisteredUser>(validUser, HttpStatus.OK);
	// } catch (Exception e) {
	// e.printStackTrace();
	// return new ResponseEntity<RegisteredUser>(validUser, HttpStatus.BAD_REQUEST);
	// }
	// }

	@Operation(summary = "Get user", description = "Get user", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = RegisteredUser.class)) }),
			@ApiResponse(responseCode = "400", description = "Not possible to get user.", content = @Content)
	})
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RegisteredUser> getUserById(
			@Parameter(name = "id", description = "ID of user", required = true) @PathVariable("id") Long id) {
		RegisteredUser user = userService.findOne(id);
		return new ResponseEntity<RegisteredUser>(user, HttpStatus.OK);
	}

	@Operation(summary = "Activate user", description = "Activate user", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = RegisteredUser.class)) }),
			@ApiResponse(responseCode = "400", description = "Not possible to activate user.", content = @Content)
	})
	@GetMapping(value = "/activate/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthenticationResponse> activateUser(
			@Parameter(name = "Activation Token", description = "User activation token", required = true) @PathVariable("token") String token)
			throws Exception {
		userService.activateUser(token);
		AuthenticationResponse response = new AuthenticationResponse();
		response.setToken("Activated");
		return new ResponseEntity<AuthenticationResponse>(response, HttpStatus.OK);
	}

	@GetMapping("/visitedUsers")
	public ResponseEntity<List<RegisterdUserDTO>> getVisitedUsers(@RequestParam("adminId") Long adminId,
																  @RequestParam("sortType") String sortType){
		List<RegisterdUserDTO> users = userService.getVisitedUsers(adminId, sortType);
		return ResponseEntity.ok(users);
	}

}
