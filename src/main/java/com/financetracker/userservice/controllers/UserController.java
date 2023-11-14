package com.financetracker.userservice.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.financetracker.userservice.modal.User;
import com.financetracker.userservice.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
public class UserController {
	
	private final UserService userService;
	

	@GetMapping("/users") //another test comment
	@Operation(summary = "Get users", description = "Get all users", tags = {"User services" })
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users =  userService.getAllUsers();
		ResponseEntity<List<User>> response = new ResponseEntity<>(users,HttpStatus.OK);
		return response;
	}
	
	
	@PostMapping("/user")
	@Operation(summary = "Create user", description = "Create a new user", tags = {"User services"})
	@ApiResponses(value = {@ApiResponse(responseCode = "201", description = "New user is created. refer location header on the response for new resource")})
	public ResponseEntity<User> createUser(@RequestBody User user){
		User createdUser = userService.createUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
}
