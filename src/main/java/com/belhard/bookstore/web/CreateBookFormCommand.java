package com.belhard.bookstore.web;

import jakarta.servlet.http.HttpServletRequest;

public class CreateBookFormCommand implements Command {
    @Override
    public String process(HttpServletRequest req) {
        return "jsp/bookForm.jsp";
    }
}
