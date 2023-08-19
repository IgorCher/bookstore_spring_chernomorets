package com.belhard.bookstore.data.dao.impl;

import com.belhard.bookstore.data.dao.OrderItemDao;
import com.belhard.bookstore.data.entity.Order;
import com.belhard.bookstore.data.entity.OrderItem;

import java.util.List;

public class OrderItemDaoImpl implements OrderItemDao {
    @Override
    public OrderItem findById(Long id) {
        return null;
    }

    @Override
    public List<OrderItem> findAll() {
        return null;
    }

    @Override
    public List<OrderItem> findByOrderId(Order order) {
        return null;
    }

    @Override
    public OrderItem create(OrderItem orderItem) {
        return null;
    }

    @Override
    public OrderItem update(OrderItem orderItem) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
