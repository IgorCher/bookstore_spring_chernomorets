package com.belhard.bookstore.data.dao;

import com.belhard.bookstore.data.dto.OrderItemDto;

import java.util.List;

public interface OrderItemDao {
    OrderItemDto findById(Long id);

    List<OrderItemDto> findAll();

    List<OrderItemDto> findByOrderId(Long orderId);

    OrderItemDto create(OrderItemDto orderItemDto);

    OrderItemDto update(OrderItemDto orderItemDto);

    boolean delete(Long id);
}
