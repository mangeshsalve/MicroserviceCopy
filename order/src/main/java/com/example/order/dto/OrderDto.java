package com.example.order.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class OrderDto {

    private int id;
    @NotEmpty(message = "At least one item required")
    private List<ItemsDto> items;
    @NotBlank(message = "Order status is required")
    private String status;
    private int userId;
    
    
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<ItemsDto> getItems() {
		return items;
	}
	public void setItems(List<ItemsDto> items) {
		this.items = items;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
