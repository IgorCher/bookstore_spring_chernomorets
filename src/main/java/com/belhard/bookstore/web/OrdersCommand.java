package com.belhard.bookstore.web;

import com.belhard.bookstore.service.dto.OrderDto;
import com.belhard.bookstore.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller("orders")
@RequiredArgsConstructor
public class OrdersCommand implements Command {
    private final OrderService orderService;

    @Override
    public String process(HttpServletRequest req) {
        List<OrderDto> orders = orderService.getAll();
        req.setAttribute("orders", orders);
        return "jsp/orders.jsp";
    }
}
