package com.example.demo.auth;

import java.io.IOException;
import java.util.List;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.service.JWTService;
import com.example.demo.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
	@Autowired
	private  JWTService jwt;
	
	@Autowired
	UserService service;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=null;
		String token=null;
		String header=request.getHeader("Authorization");
		if(header!=null && header.startsWith("Bearer ")) {
			 token =header.substring(7);
			username = jwt.extractUsername(token);
		}
		
	       if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	            UserDetails userDetails = service.loadUserByUsername(username);
	            if (jwt.validateToken(token, userDetails.getUsername())) {
	                UsernamePasswordAuthenticationToken authToken =
	                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	                SecurityContextHolder.getContext().setAuthentication(authToken);
	            }
	        }
		
		filterChain.doFilter(request, response);
	}

}
