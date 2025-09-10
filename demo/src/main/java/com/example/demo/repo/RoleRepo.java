package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Roles;
@Repository
public interface RoleRepo extends JpaRepository<Roles, Integer> {
	Optional<Roles> findByName(String name);
}
