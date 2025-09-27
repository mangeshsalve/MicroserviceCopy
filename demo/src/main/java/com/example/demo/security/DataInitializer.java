package com.example.demo.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.model.Roles;
import com.example.demo.repo.RoleRepo;
@Component
public class DataInitializer implements CommandLineRunner {

	private RoleRepo repo;
	
	public DataInitializer(RoleRepo rp) {
		this.repo=rp;
	}
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
	       if (repo.findByName("ROLE_USER").isEmpty()) {
	            Roles userRole = new Roles();
	            userRole.setName("ROLE_USER");
	            repo.save(userRole);
	        }

	        if (repo.findByName("ROLE_ADMIN").isEmpty()) {
	            Roles adminRole = new Roles();
	            adminRole.setName("ROLE_ADMIN");
	            repo.save(adminRole);
	        }
	}

}
