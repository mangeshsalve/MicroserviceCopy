package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationDto {
	@NotBlank(message = "Username is mandatory")
    private String username;
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Email should be valid")
    private String email;
	@Size(min = 6)
    private String password; 
    private String firstName;
    private String lastName;
    private boolean active;
}
