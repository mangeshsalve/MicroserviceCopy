package com.example.demo.service;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JWTService {
	
	@Value("${jwt.secret}")
	private String secret;
	private  SecretKey SECRATE_KEY;

	
	  // ✅ Initialize secretKey after @Value injection
    @PostConstruct
    public void init() {
        this.SECRATE_KEY = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
	
	public String generate(String username,int id,Collection<String> roles) {
	
		return Jwts.builder()
				.subject(username)
				.claim("roles", roles)
				.claim("userId", id)
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
	
	public String extractUsername(String token) {
	        return extractClaim(token, Claims::getSubject);
	}
	  
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = Jwts.parser()
	                .verifyWith(SECRATE_KEY)
	                .build()
	                .parseSignedClaims(token)
	                .getPayload();
	        return claimsResolver.apply(claims);
	 }
	    
	 public boolean isTokenExpired(String token) {
	        return extractClaim(token, Claims::getExpiration).before(new Date());
	 }

	 public boolean validateToken(String token, String username) {
	        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
	 }	    

}
