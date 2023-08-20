package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.data.dto.OrderDto;
import com.belhard.bookstore.data.entity.Order;
import com.belhard.bookstore.data.repository.OrderRepository;
import com.belhard.bookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public OrderDto getById(Long id) {
        Order order = orderRepository.find(id);
        return toDto(order);
    }

    @Override
    public List<OrderDto> getAll() {
        return orderRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public List<OrderDto> getByUserId(Long userId) {
        return orderRepository.findByUserId(userId)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public OrderDto toDto(Order entity) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(entity.getId());
        orderDto.setUserId(entity.getUser().getId());
        orderDto.setTotalCost(entity.getTotalCost());
        return orderDto;
    }
}
