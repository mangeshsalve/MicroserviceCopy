package com.example.demo.auth;

import java.io.IOException;
import java.util.List;

import org.hibernate.annotations.Comment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.service.JWTService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
	
	private final JWTService jwt;
	
	public JwtAuthFilter(JWTService service) {
		this.jwt=service;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String header=request.getHeader("Authorization");
		if(header!=null && header.startsWith("Bearer ")) {
			String token =header.substring(7);
			var claim= jwt.parse(token).getPayload();
			var authorities=((List<String>)claim.get("roles")).stream().map(SimpleGrantedAuthority::new).toList();
			  var auth = new UsernamePasswordAuthenticationToken(claim.getSubject(), null, authorities);
		      SecurityContextHolder.getContext().setAuthentication(auth);
		}
		filterChain.doFilter(request, response);
	}

}
