package com.belhard.bookstore.data.repository.impl;

import com.belhard.bookstore.data.dao.BookDao;
import com.belhard.bookstore.data.dao.OrderDao;
import com.belhard.bookstore.data.dao.OrderItemDao;
import com.belhard.bookstore.data.dao.UserDao;
import com.belhard.bookstore.data.dto.OrderDto;
import com.belhard.bookstore.data.dto.OrderItemDto;
import com.belhard.bookstore.data.entity.Book;
import com.belhard.bookstore.data.entity.Order;
import com.belhard.bookstore.data.entity.OrderItem;
import com.belhard.bookstore.data.entity.User;
import com.belhard.bookstore.data.mapper.DataMapper;
import com.belhard.bookstore.data.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
    private final OrderDao orderDao;
    private final OrderItemDao orderItemDao;
    private final UserDao userDao;
    private final BookDao bookDao;
    private final DataMapper dataMapper;

    @Override
    public Order find(Long id) {
        OrderDto orderDto = orderDao.findById(id);
        return combineOrder(orderDto);
    }

    @Override
    public List<Order> findAll() {
        return orderDao.findAll()
                .stream()
                .map(this::combineOrder)
                .toList();
    }

    @Override
    public List<Order> findByUserId(Long userId) {
        return orderDao.findByUserId(userId)
                .stream()
                .map(this::combineOrder)
                .toList();
    }

    @Override
    public Order create(Order entity) {
        OrderDto orderDto = dataMapper.toDto(entity);
        OrderDto createdDto = orderDao.create(orderDto);
        entity.getOrderItems().forEach(orderItem -> {
            OrderItemDto orderItemDto = dataMapper.toDto(orderItem);
            orderItemDao.create(orderItemDto);
        });
        return combineOrder(createdDto);
    }

    @Override
    public Order update(Order entity) {
        OrderDto orderDto = dataMapper.toDto(entity);
        OrderDto updatedDto = orderDao.update(orderDto);
        orderItemDao.findByOrderId(entity.getId())
                .forEach(orderItemDto -> orderItemDao.delete(orderItemDto.getId()));
        entity.getOrderItems().forEach(orderItem -> {
            OrderItemDto orderItemDto = dataMapper.toDto(orderItem);
            orderItemDao.create(orderItemDto);
        });
        return combineOrder(updatedDto);
    }

    @Override
    public boolean delete(Long id) {
        orderItemDao.findByOrderId(id).forEach(orderItemDto -> orderItemDao.delete(orderItemDto.getId()));
        if (!orderDao.delete(id)) {
            return false;
        }
        return true;
    }

    private Order combineOrder(OrderDto orderDto) {
        Order order = dataMapper.toEntity(orderDto);
        User user = dataMapper.toEntity(userDao.find(orderDto.getUserId()));
        order.setUser(user);
        List<OrderItemDto> itemsDto = orderItemDao.findByOrderId(orderDto.getId());
        List<OrderItem> items = new ArrayList<>();
        itemsDto.forEach(dto -> {
            OrderItem entity = dataMapper.toEntity(dto);
            Book book = dataMapper.toEntity(bookDao.find(dto.getBookId()));
            entity.setBook(book);
        });
        order.setOrderItems(items);
        return order;
    }
}
