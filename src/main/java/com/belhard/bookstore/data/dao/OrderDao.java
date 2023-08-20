package com.belhard.bookstore.data.dao;

import com.belhard.bookstore.data.dto.OrderDto;

import java.util.List;

public interface OrderDao {
    OrderDto findById(Long id);

    List<OrderDto> findAll();

    List<OrderDto> findByUserId(Long userId);

    OrderDto create(OrderDto orderDto);

    OrderDto update(OrderDto orderDto);

    boolean delete(Long id);
}
