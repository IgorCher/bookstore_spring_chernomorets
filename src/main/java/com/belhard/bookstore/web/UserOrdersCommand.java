package com.belhard.bookstore.web;

import com.belhard.bookstore.data.dto.OrderDto;
import com.belhard.bookstore.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller("user_orders")
@RequiredArgsConstructor
public class UserOrdersCommand implements Command {
    private final OrderService orderService;

    @Override
    public String process(HttpServletRequest req) {
        String idRaw = req.getParameter("id");
        Long id = Long.valueOf(idRaw);
        List<OrderDto> orders = orderService.getByUserId(id);
        req.setAttribute("orders", orders);
        return "jsp/userOrders.jsp";
    }
}
