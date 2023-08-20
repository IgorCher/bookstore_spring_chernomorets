package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.data.entity.Order;
import com.belhard.bookstore.data.repository.OrderRepository;
import com.belhard.bookstore.service.OrderService;
import com.belhard.bookstore.service.dto.OrderDto;
import com.belhard.bookstore.service.mapper.DataMapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final DataMapperService dataMapperService;

    @Override
    public OrderDto getById(Long id) {
        Order order = orderRepository.find(id);
        return dataMapperService.toDto(order);
    }

    @Override
    public List<OrderDto> getAll() {
        return orderRepository.findAll()
                .stream()
                .map(dataMapperService::toDto)
                .toList();
    }

    @Override
    public List<OrderDto> getByUserId(Long userId) {
        return orderRepository.findByUserId(userId)
                .stream()
                .map(dataMapperService::toDto)
                .toList();
    }
}
