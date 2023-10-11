package com.belhard.bookstore.web.controller;

import com.belhard.bookstore.service.OrderService;
import com.belhard.bookstore.service.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    public String getOne(@PathVariable long id, Model model) {
        OrderDto orderDto = orderService.getById(id);
        model.addAttribute("order", orderDto);
        return "order";
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String getAll(Model model) {
        List<OrderDto> orders = orderService.getAll();
        model.addAttribute("orders", orders);
        return "orders";
    }

    @RequestMapping(value = "/users/{id}/orders", method = RequestMethod.GET)
    public String getUserOrders(@PathVariable long id, Model model) {
        List<OrderDto> orders = orderService.getByUserId(id);
        model.addAttribute("orders", orders);
        return "userOrders";
    }
}
