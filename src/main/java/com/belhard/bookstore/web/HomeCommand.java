package com.belhard.bookstore.web;

import jakarta.servlet.http.HttpServletRequest;

public class HomeCommand implements Command {
    @Override
    public String process(HttpServletRequest req) {
        return "jsp/home.jsp";
    }
}
