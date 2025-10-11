package com.example.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.order.dto.OrderDto;
import com.example.order.model.Orders;
import com.example.order.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
    private OrderService orderService;

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public List<OrderDto> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/{id}")
    public OrderDto getOrder(
            @PathVariable int id
    ) {
        return orderService.getOrder(id);
    }

    @PostMapping("/addOrder")
    @PreAuthorize("hasRole('USER')")
    public Orders addOrder(
            @Validated @RequestBody Orders orderDto,
            @RequestHeader("Authorization") String authHeader
    ) {
    	 String token = authHeader.substring(7);
    	// int userId = jwtUtil.extractUserId(token);
        return orderService.addOrder(orderDto);
    }

    @PutMapping("/update")
    public String updateOrder(
            @Validated @RequestBody OrderDto orderDto
    ) {
        return orderService.updateOrder(orderDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrder(
            @PathVariable int id
    ) {
       orderService.deleteOrder(id);
    }
}
