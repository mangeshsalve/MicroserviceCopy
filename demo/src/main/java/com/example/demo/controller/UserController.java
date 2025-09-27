package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.dto.UsersDto;
import com.example.demo.model.Users;
import com.example.demo.service.UserService;



@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/test")
    public String getTest() {
    	return "Testing Done";
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UsersDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public UsersDto getUser(@PathVariable int id ) {
        return userService.getUser(id);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String>  addUser(
            @Validated @RequestBody UserRegistrationDto userRegistrationDto
    ) {
        return userService.addUser(userRegistrationDto);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public Users updateUser(
            @Validated @RequestBody UsersDto userDto
    ) {
        return userService.updateUser(userDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(
            @PathVariable int id
    ) {
         userService.deleteUser(id);
    }


}
