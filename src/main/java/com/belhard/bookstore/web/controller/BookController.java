package com.belhard.bookstore.web.controller;

import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.dto.BookDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @GetMapping("/{id}")
    public String getOne(@PathVariable long id, Model model) {
        BookDto bookDto = bookService.getById(id);
        model.addAttribute("book", bookDto);
        return "book";
    }

    @GetMapping
    public String getAll(Model model) {
        List<BookDto> books = bookService.getAll();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable long id, Model model) {
        BookDto bookDto = bookService.getById(id);
        model.addAttribute("book", bookDto);
        return "editBookForm";
    }

    @PostMapping("/{id}/edit")
    public String edit(@ModelAttribute BookDto bookDto) {
        bookService.update(bookDto);
        return "redirect:/books/" + bookDto.getId();
    }

    @GetMapping("/create")
    public String createForm() {
        return "/bookForm";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute BookDto bookDto) {
        BookDto created = bookService.update(bookDto);
        return "redirect:/books/" + created.getId();
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable long id) {
        bookService.delete(id);
        return "redirect:/books";
    }
}
