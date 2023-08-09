package com.belhard.bookstore.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

@Controller("create_user_form")
public class CreateUserFormCommand implements Command {
    @Override
    public String process(HttpServletRequest req) {
        return "jsp/userForm.jsp";
    }
}
