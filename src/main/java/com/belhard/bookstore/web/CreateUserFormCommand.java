package com.belhard.bookstore.web;

import jakarta.servlet.http.HttpServletRequest;

public class CreateUserFormCommand implements Command {
    @Override
    public String process(HttpServletRequest req) {
        return "jsp/userForm.jsp";
    }
}
