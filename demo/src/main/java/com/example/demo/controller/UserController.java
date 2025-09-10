package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    public List<UsersDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping
    public UsersDto getUser(
            @RequestParam(name = "id") int id
    ) {
        return userService.getUser(id);
    }

    @PostMapping
    public ResponseEntity<String>  addUser(
            @Validated @RequestBody UserRegistrationDto userRegistrationDto
    ) {
    	System.out.println("inside controller ***********************************************");
        return userService.addUser(userRegistrationDto);
    }

    @PutMapping
    public Users updateUser(
            @Validated @RequestBody UsersDto userDto
    ) {
        return userService.updateUser(userDto);
    }

    @DeleteMapping
    public void deleteUser(
            @RequestParam(name = "id") int id
    ) {
         userService.deleteUser(id);
    }


}
