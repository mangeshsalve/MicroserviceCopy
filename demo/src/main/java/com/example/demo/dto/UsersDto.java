package com.example.demo.dto;

import java.util.Set;

import lombok.Data;

@Data
public class UsersDto {

    private int id;
    private boolean active;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Set<String> roles;
    
    
	@Override
	public String toString() {
		return "UsersDto [id=" + id + ", active=" + active + ", username=" + username + ", email=" + email
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", role=" + roles + "]";
	}
	
}
