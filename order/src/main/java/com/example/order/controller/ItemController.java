package com.example.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.order.dto.ItemsDto;
import com.example.order.dto.OrderDto;
import com.example.order.model.Orders;
import com.example.order.service.ItemsService;
import com.example.order.service.OrderService;

@RestController
@RequestMapping("/item")
public class ItemController {
	

	@Autowired
    private ItemsService itemService;

    @GetMapping("/all")
    public List<ItemsDto> getItems() {
        return itemService.getItems();
    }

    @GetMapping
    public ItemsDto getOrder(
            @RequestParam(name = "id") int id
    ) {
        return itemService.getItem(id);
    }

    @PostMapping
    public String addOrder(
            @Validated @RequestBody ItemsDto orderDto
    ) {
        return itemService.addItem(orderDto);
    }

    @PutMapping
    public String updateOrder(
            @Validated @RequestBody ItemsDto orderDto
    ) {
        return itemService.updateItem(orderDto);
    }

    @DeleteMapping
    public void deleteOrder(
            @RequestParam(name = "id") int id
    ) {
       itemService.deleteItem(id);
    }

}
