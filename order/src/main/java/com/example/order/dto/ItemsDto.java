package com.example.order.dto;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class ItemsDto {
	private int itemId;
    private String name;
    private String description;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private String productCode;
    private String category;
    private String imageUrl;
    private BigDecimal discount;
    private String currency;
}
