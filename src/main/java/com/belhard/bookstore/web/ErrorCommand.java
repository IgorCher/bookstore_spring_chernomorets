package com.belhard.bookstore.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

@Controller("error")
public class ErrorCommand implements Command {
    @Override
    public String process(HttpServletRequest req) {
        return "jsp/error.jsp";
    }
}
