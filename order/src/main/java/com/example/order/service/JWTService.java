package com.example.order.service;

import java.nio.charset.StandardCharsets;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JWTService {

	@Value(value = "${jwt.token}")
	private String secret;
	
	private SecretKey key;

	@PostConstruct
	public void init() {
		this.key=Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	}
	
	//parse and validate jwt
	public Jws<Claims> parse(String token){
		return Jwts.parser()
				.verifyWith(key)
				.build()
				.parseSignedClaims(token);
	}

	public String extractUsername(String token) {
        return parse(token).getPayload().getSubject();
}
  
    public <T> T extractClaim(String token, String claimName, Class<T> type) {
        try {
            return parse(token).getPayload().get(claimName, type);
        } catch (Exception e) {
            throw new RuntimeException("Unable to extract claim: " + claimName);
        }
    }

	public int extractUserId(String token) {
		 return parse(token).getPayload().get("userId", Integer.class);
	}
}
