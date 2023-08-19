package com.belhard.bookstore.data.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDto {
    private Long id;
    private Long bookId;
    private Integer bookQuantity;
    private BigDecimal bookPrice;
}
