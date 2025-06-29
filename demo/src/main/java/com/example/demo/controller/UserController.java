package com.example.demo.controller;

import com.example.demo.model.UserDto;
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

    @GetMapping("/all")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping
    public Optional<UserDto> getUser(
            @RequestParam(name = "id") int id
    ) {
        return userService.getUser(id);
    }

    @PostMapping
    public UserDto addUser(
            @Validated @RequestBody UserDto userDto
    ) {
        return userService.addUser(userDto);
    }

    @PutMapping
    public UserDto updateUser(
            @Validated @RequestBody UserDto userDto
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
