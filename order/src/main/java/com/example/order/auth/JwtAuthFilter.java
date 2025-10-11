package com.example.order.auth;

import com.example.order.service.JWTService;
//import com.example.order.service.UserService; // optional, if you load users (can remove if unused)
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JwtAuthFilter validates every request using the JWT token.
 * It extracts the username and roles from the token and sets authentication in the SecurityContext.
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JWTService jwtService;

    public JwtAuthFilter(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        String token = null;

        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
            try {
                // Extract username & roles from JWT
                String username = jwtService.extractUsername(token);
                List<String> roles = jwtService.parse(token)
                        .getPayload()
                        .get("roles", List.class);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // Convert roles into Spring Security authorities
                    List authorities = roles.stream()
                            .map(role -> "ROLE_" + role.replace("ROLE_", "")) // ensure proper prefix
                            .map(org.springframework.security.core.authority.SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());

                    UserDetails userDetails = User.withUsername(username)
                            .password("") // not needed, we only verify JWT
                            .authorities(authorities)
                            .build();

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } catch (Exception e) {
                System.out.println ("Invalid JWT token: {} "+ e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }
}
