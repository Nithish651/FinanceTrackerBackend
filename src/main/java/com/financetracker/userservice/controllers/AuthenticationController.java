package com.financetracker.userservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.financetracker.authflow.JwtService;
import com.financetracker.userservice.modal.LoginRequest;
import com.financetracker.userservice.repository.UserRepository;



@RestController
public class AuthenticationController {

	
	private AuthenticationManager authenticationManager;
	private UserRepository userRepository;
	private JwtService jwtService;
	
	public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository, JwtService jwtService) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.jwtService = jwtService;
	}
	
	
	@PostMapping("/users/auth/login")
	public ResponseEntity<String> authenticate(@RequestBody LoginRequest loginRequest){
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		var user = userRepository.findByUserName(loginRequest.getUsername());
		var token = jwtService.createToken(user.getUserName());
		return ResponseEntity.ok(token);
	}
	
}
