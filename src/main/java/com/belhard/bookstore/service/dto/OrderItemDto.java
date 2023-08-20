package com.belhard.bookstore.service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDto {
    private Long id;
    private Long orderId;
    private BookDto bookDto;
    private Integer bookQuantity;
    private BigDecimal bookPrice;
}
