package com.belhard.bookstore.service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private UserDto userDto;
    private BigDecimal totalCost;
    private List<OrderItemDto> orderItemsDto;
}
