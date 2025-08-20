package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UsersDto;
import com.example.demo.model.Users;
import com.example.demo.repo.UserRepo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService {

    
    @Autowired
    private UserRepo userRepo;


    public List<UsersDto> getUsers() {
    	List<Users> all = userRepo.findAll();
    	List<UsersDto> collect = all.stream().map(
    			
    			user -> {
    				
    				UsersDto dto=new UsersDto();
    				dto.setId(user.getId());
    				dto.setName(user.getName());
    				dto.setActive(user.isActive());
    				return dto;
    			}
    			
    			
    			).collect(Collectors.toList());
    	
        return collect;
    }

    public UsersDto getUser(int id) {
    	Optional<Users> byId = userRepo.findById(id);
    	UsersDto dto=new UsersDto();
    	byId.ifPresent(user->{
    		dto.setId(user.getId());
    		dto.setName(user.getName());
    		dto.setActive(user.isActive());
    		
    	});
    	
        return dto;
    }

    public Users addUser(UsersDto userDto) {
    	Users users=new Users();
    	users.setId(userDto.getId());
    	users.setName(userDto.getName());
    	users.setActive(userDto.isActive());

        return userRepo.save(users);
    }

    public Users updateUser(UsersDto userDto) {
    	Optional<Users> byId = userRepo.findById(userDto.getId());
    	Users users = byId.get();
    	
    	users.setName(userDto.getName());
    	users.setActive(userDto.isActive());
    	userRepo.save(users);
        return users;
    }

    public void deleteUser(int id) {

       userRepo.deleteById(id);;
    }


}
