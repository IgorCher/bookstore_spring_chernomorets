package com.belhard.bookstore.service;

import com.belhard.bookstore.data.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto getById(Long id);
    List<OrderDto> getAll();
    List<OrderDto> getByUserId(Long userId);
}
