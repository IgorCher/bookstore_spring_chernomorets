package com.belhard.bookstore.web;

import com.belhard.bookstore.data.dto.OrderDto;
import com.belhard.bookstore.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller("order")
@RequiredArgsConstructor
public class OrderCommand implements Command {
    private final OrderService orderService;

    @Override
    public String process(HttpServletRequest req) {
        String idRaw = req.getParameter("id");
        Long id = Long.valueOf(idRaw);
        OrderDto orderDto = orderService.getById(id);
        req.setAttribute("order", orderDto);
        return "jsp/order.jsp";
    }
}
