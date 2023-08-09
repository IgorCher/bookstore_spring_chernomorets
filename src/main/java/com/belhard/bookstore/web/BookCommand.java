package com.belhard.bookstore.web;

import com.belhard.bookstore.data.dto.BookDto;
import com.belhard.bookstore.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;

@Controller ("book")
@RequiredArgsConstructor
@Log4j2
public class BookCommand implements Command {
    private final BookService bookService;

    @Override
    public String process(HttpServletRequest req) {
        String idRaw = req.getParameter("id");
        Long id = Long.valueOf(idRaw);
        BookDto bookDto = bookService.getById(id);
        req.setAttribute("book", bookDto);
        return "jsp/book.jsp";
    }
}
