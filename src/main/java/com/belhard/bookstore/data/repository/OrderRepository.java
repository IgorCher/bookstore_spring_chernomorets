package com.belhard.bookstore.data.repository;

import com.belhard.bookstore.data.entity.Order;

import java.util.List;

public interface OrderRepository {

    Order find(Long id);

    List<Order> findAll();

    List<Order> findByUserId(Long userId);

    Order create(Order entity);

    Order update(Order entity);

    boolean delete(Long id);
}
