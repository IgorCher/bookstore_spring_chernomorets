package com.belhard.bookstore.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

@Controller("create_book_form")
public class CreateBookFormCommand implements Command {
    @Override
    public String process(HttpServletRequest req) {
        return "jsp/bookForm.jsp";
    }
}
