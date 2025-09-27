package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginDto;
import com.example.demo.service.JWTService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private  AuthenticationManager authenticationManager;
	@Autowired	
	private  JWTService jwtService;
	

	
	@RequestMapping(value  = "/login",method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> login(@RequestBody LoginDto dto){
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
		String token =jwtService.generate(dto.getUsername()	, List.of("ROLE_USER"));
		Map<String, String> of = Map.of("access_token",token);
		return ResponseEntity.status(HttpStatus.OK).body(of);
		}catch(Exception ex){
		      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                      .body(Map.of("error", "Invalid username or password")); 
		}
		
	}




}
