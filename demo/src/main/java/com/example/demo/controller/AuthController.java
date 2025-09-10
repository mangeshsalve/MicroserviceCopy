package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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


	private final AuthenticationManager authenticationManager;
	
	private final JWTService jwtService;
	
	   public AuthController(AuthenticationManager authenticationManager, JWTService jwtService) {
	        this.authenticationManager = authenticationManager;
	        this.jwtService = jwtService;
	    }
	
	@RequestMapping(name = "/login",method = RequestMethod.POST)
	public Map<String, String> login(@RequestBody LoginDto dto){
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
		String token =jwtService.generate(dto.getUsername()	, List.of("ROLE_USER"));
		return Map.of("access_token",token);
	}
}
