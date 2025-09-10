package com.example.demo.service;


import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.dto.UsersDto;
import com.example.demo.model.Roles;
import com.example.demo.model.Users;
import com.example.demo.repo.RoleRepo;
import com.example.demo.repo.UserRepo;


@Service
public class UserService {
	@Autowired
	private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private RoleRepo roleRepo;


    public List<UsersDto> getUsers() {
    	List<Users> all = userRepo.findAll();
    	List<UsersDto> collect = all.stream().map(
    			
    			user -> {
    				
    				UsersDto dto=new UsersDto();
    				dto.setId(user.getId());
    				dto.setFirstName(user.getFirstName());
    				dto.setLastName(user.getLastName());
    				
    				dto.setEmail(user.getEmail());
    				dto.setUsername(user.getUsername());
    				dto.setActive(user.isActive());
    				
    				Set<String> collectRoleNames = user.getRoles().stream().map(Roles :: getName).collect(Collectors.toSet());
    	    		dto.setRoles(collectRoleNames);
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
			dto.setFirstName(user.getFirstName());
			dto.setLastName(user.getLastName());
		
			dto.setEmail(user.getEmail());
			dto.setUsername(user.getUsername());
			dto.setActive(user.isActive());
			
			Set<String> collectRoleNames = user.getRoles().stream().map(Roles :: getName).collect(Collectors.toSet());
    		dto.setRoles(collectRoleNames);
    	});
    	
        return dto;
    }

    public ResponseEntity<String> addUser(UserRegistrationDto  userRegistrationDto) {
    	Users users=new Users();
    	users.setFirstName(userRegistrationDto.getFirstName());
    	users.setLastName(userRegistrationDto.getLastName());
    	users.setEmail(userRegistrationDto.getEmail());
    	users.setUsername(userRegistrationDto.getUsername());
    	users.setActive(userRegistrationDto.isActive());
    	
    	Roles roles = roleRepo.findByName("ROLE_USER").orElseThrow(()-> new RuntimeException("Default Role Not found"));
    	users.getRoles().add(roles);
    	users.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
    	
    	Users save = userRepo.save(users);
    	
    	if(save!=null) {
    		return new ResponseEntity<String>("User save Successfully",HttpStatusCode.valueOf(200));
    	}else {
    		return new ResponseEntity<String>("User Not save",HttpStatusCode.valueOf(400));
    	}
    	
    }

    public Users updateUser(UsersDto userDto) {
    	Optional<Users> byId = userRepo.findById(userDto.getId());
    	Users users = byId.get();
    	
    	users.setFirstName(userDto.getFirstName());
    	users.setLastName(userDto.getLastName());
    	users.setActive(userDto.isActive());
    	userRepo.save(users);
        return users;
    }

    public void deleteUser(int id) {

       userRepo.deleteById(id);;
    }


}
