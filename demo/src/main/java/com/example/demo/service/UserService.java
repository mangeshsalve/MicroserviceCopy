package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserDto;
import com.example.demo.repo.UserRepo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService {

    private List<UserDto> users;
    
    @Autowired
    private UserRepo userRepo;


    public List<UserDto> getUsers() {
        return userRepo.findAll();
    }

    public Optional<UserDto> getUser(int id) {
        return userRepo.findById(id);
    }

    public UserDto addUser(UserDto userDto) {

        return userRepo.save(userDto);
    }

    public UserDto updateUser(UserDto userDto) {
        Optional<UserDto> userOptional = users.stream()
                .filter(null)
                .map(user -> {
                    user.setName(userDto.getName());
                    user.setActive(userDto.isActive());
                    return user;
                }).findFirst();

        return userOptional.isPresent() ? userOptional.get() : null;
    }

    public void deleteUser(int id) {

       userRepo.deleteById(id);;
    }


}
