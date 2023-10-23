package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.data.entity.Order;
import com.belhard.bookstore.data.repository.OrderRepository;
import com.belhard.bookstore.service.OrderService;
import com.belhard.bookstore.service.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ModelMapper mapper;

    @Override
    public OrderDto getById(Long id) {
        Order order = orderRepository.find(id);
        return mapper.map(order, OrderDto.class);
    }

    @Override
    public List<OrderDto> getAll() {
        return orderRepository.findAll()
                .stream()
                .map(o -> mapper.map(o, OrderDto.class))
                .toList();
    }

    @Override
    public List<OrderDto> getByUserId(Long userId) {
        return orderRepository.findByUserId(userId)
                .stream()
                .map(o -> mapper.map(o, OrderDto.class))
                .toList();
    }
}
