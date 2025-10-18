package com.example.demo.controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.auth.JwtAuthFilter;
import com.example.demo.config.SecurityConfig;
import com.example.demo.dto.LoginDto;
import com.example.demo.model.Users;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.JWTService;

@RestController
@RequestMapping("/auth")
public class AuthController{

	@Autowired
	private  AuthenticationManager authenticationManager;
	@Autowired	
	private  JWTService jwtService;
	
	@Autowired
	private UserRepo userRepo;
	
	
	@RequestMapping(value  = "/login",method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> login(@RequestBody LoginDto dto){
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        Users user = userRepo.findByEmail(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        // Extract roles dynamically
        List<String> roles = user.getRoles().stream()
                .map(role -> role.getName())
                .toList();
		
		String token =jwtService.generate(user.getUsername(),user.getId(),roles);
		Map<String, String> of = Map.of("access_token",token);
		return ResponseEntity.status(HttpStatus.OK).body(of);
		}catch(Exception ex){
		      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                      .body(Map.of("error", "Invalid username or password")); 
		}
		
	}




}
