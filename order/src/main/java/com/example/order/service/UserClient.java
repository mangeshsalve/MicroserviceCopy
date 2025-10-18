package com.example.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.order.dto.UserDto;

@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/user/{id}")
    UserDto getUserById(@PathVariable("id") int id);
}
