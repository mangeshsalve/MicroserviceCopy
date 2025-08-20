package com.example.order.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Items {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
