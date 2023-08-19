package com.belhard.bookstore.data.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDto {
    private Long id;
    private Long userID;
    private BigDecimal totalCost;
}