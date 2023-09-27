package com.belhard.bookstore.web.controller;

import com.belhard.bookstore.service.OrderService;
import com.belhard.bookstore.service.dto.OrderDto;
import com.belhard.bookstore.service.dto.OrderItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    public String getOne(@PathVariable long id, Model model) {
        OrderDto orderDto = orderService.getById(id);
        List<OrderItemDto> items = orderDto.getOrderItemsDto();
        model.addAttribute("order", orderDto);
        return "order";
    }

    @GetMapping
    public String getAll(Model model) {
        List<OrderDto> orders = orderService.getAll();
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/user_orders/{id}")
    public String getUserOrders(@PathVariable long id, Model model) {
        List<OrderDto> orders = orderService.getByUserId(id);
        model.addAttribute("orders", orders);
        return "userOrders";
    }
}
