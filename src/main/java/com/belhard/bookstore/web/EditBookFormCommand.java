package com.belhard.bookstore.web;

import com.belhard.bookstore.service.dto.BookDto;
import com.belhard.bookstore.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller("edit_book_form")
@RequiredArgsConstructor
public class EditBookFormCommand implements Command {
    public final BookService bookService;

    @Override
    public String process(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("id"));
        BookDto bookDto = bookService.getById(id);
        req.setAttribute("book", bookDto);
        return "jsp/editBookForm.jsp";
    }
}
