package com.belhard.bookstore.data.dao;

import com.belhard.bookstore.data.entity.Order;
import com.belhard.bookstore.data.entity.OrderItem;

import java.util.List;

public interface OrderItemDao {
    OrderItem findById(Long id);

    List<OrderItem> findAll();

    List<OrderItem> findByOrderId(Order order);

    OrderItem create(OrderItem orderItem);

    OrderItem update(OrderItem orderItem);

    boolean delete(Long id);
}
