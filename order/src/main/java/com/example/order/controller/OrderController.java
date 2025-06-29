package com.example.order.controller;

import com.example.order.model.OrderDto;
import com.example.order.service.OrderService;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public List<OrderDto> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping
    public Optional<OrderDto> getOrder(
            @RequestParam(name = "id") int id
    ) {
        return orderService.getOrder(id);
    }

    @PostMapping
    public OrderDto addOrder(
            @Validated @RequestBody OrderDto orderDto
    ) {
        return orderService.addOrder(orderDto);
    }

    @PutMapping
    public OrderDto updateOrder(
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
