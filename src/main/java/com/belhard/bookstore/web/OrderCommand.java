package com.belhard.bookstore.web;

import com.belhard.bookstore.service.OrderService;
import com.belhard.bookstore.service.dto.OrderDto;
import com.belhard.bookstore.service.dto.OrderItemDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller("order")
@RequiredArgsConstructor
public class OrderCommand implements Command {
    private final OrderService orderService;

    @Override
    public String process(HttpServletRequest req) {
        String idRaw = req.getParameter("id");
        Long id = Long.valueOf(idRaw);
        OrderDto orderDto = orderService.getById(id);
        List<OrderItemDto> items = orderDto.getOrderItemsDto();
        req.setAttribute("order", orderDto);
        req.setAttribute("orderItems", items);
        return "jsp/order.jsp";
    }
}
