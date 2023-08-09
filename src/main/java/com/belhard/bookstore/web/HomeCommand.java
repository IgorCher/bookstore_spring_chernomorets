package com.belhard.bookstore.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

@Controller("home")
public class HomeCommand implements Command {
    @Override
    public String process(HttpServletRequest req) {
        return "jsp/home.jsp";
    }
}
