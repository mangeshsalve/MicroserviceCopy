package com.example.order.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class OrderDto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private List<String> items;
    private String status;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<String> getItems() {
		return items;
	}
	public void setItems(List<String> items) {
		this.items = items;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

    
    
    
}
