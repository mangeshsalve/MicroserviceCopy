package com.example.demo.service;

import java.util.Collection;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

@Service
public class JWTService {
	
	private final SecretKey  SECRATE_KEY=Jwts.SIG.HS256.key().build();
	
	public String generate(String username,Collection<String> roles) {
	
		return Jwts.builder()
				.subject(username)
				.claim("roles", roles)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis()+3600_000))
				.signWith(SECRATE_KEY)
				.compact();
	}
	
	public Jws<Claims> parse(String token){
		return Jwts.parser().verifyWith(SECRATE_KEY).build().parseSignedClaims(token);
	}
	
	public SecretKey getKey() {
		return SECRATE_KEY;
	}
}
