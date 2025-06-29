package com.example.order.service;

import com.example.order.model.OrderDto;
import com.example.order.repo.OrderRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private List<OrderDto> orders;

    @Autowired
    private OrderRepo orderRepo;

    public List<OrderDto> getOrders() {
        return orderRepo.findAll();
    }

    public Optional<OrderDto> getOrder(int id) {
        return orderRepo.findById(id);
    }

    public OrderDto addOrder(OrderDto orderDto) {

        return orderRepo.save(orderDto);
    }

    public OrderDto updateOrder(OrderDto orderDto) {
        Optional<OrderDto> orderOptional = orders.stream()
                .filter(order -> order.getId() == orderDto.getId())
                .map(order -> {
                    order.setItems(orderDto.getItems());
                    order.setStatus(orderDto.getStatus());
                    return order;
                }).findFirst();

        return orderOptional.isPresent() ? orderOptional.get() : null;
    }

    public void deleteOrder(Integer id) {

        orderRepo.deleteById(id);
    }
}
