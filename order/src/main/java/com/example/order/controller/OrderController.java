package com.example.order.controller;

import com.example.order.dto.OrderDto;
import com.example.order.model.Orders;
import com.example.order.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
    private OrderService orderService;

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public List<OrderDto> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping
    public OrderDto getOrder(
            @RequestParam(name = "id") int id
    ) {
        return orderService.getOrder(id);
    }

    @PostMapping
    public Orders addOrder(
            @Validated @RequestBody Orders orderDto
    ) {
        return orderService.addOrder(orderDto);
    }

    @PutMapping
    public String updateOrder(
            @Validated @RequestBody OrderDto orderDto
    ) {
        return orderService.updateOrder(orderDto);
    }

    @DeleteMapping
    public void deleteOrder(
            @RequestParam(name = "id") int id
    ) {
       orderService.deleteOrder(id);
    }
}
