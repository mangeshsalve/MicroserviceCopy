package com.example.order.dto;

import java.util.List;

public class OrderDto {

    private int id;
    private List<ItemsDto> items;
    private String status;
    
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
