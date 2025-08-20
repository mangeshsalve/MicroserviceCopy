package com.example.demo.controller;

import com.example.demo.dto.UsersDto;
import com.example.demo.model.Users;
import com.example.demo.service.UserService;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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
    public Users addUser(
            @Validated @RequestBody UsersDto userDto
    ) {
        return userService.addUser(userDto);
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
