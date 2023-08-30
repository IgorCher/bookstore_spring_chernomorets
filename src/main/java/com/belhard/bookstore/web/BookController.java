package com.belhard.bookstore.web;

import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.dto.BookDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping(value = "/books")
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

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable long id, Model model) {
        BookDto bookDto = bookService.getById(id);
        model.addAttribute("book", bookDto);
        return "editBookForm";
    }

    @PostMapping("/edit")
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
        BookDto created = bookService.create(bookDto);
        return "redirect:/books/" + created.getId();
    }

    @PostMapping("/delete")
    public String delete(@RequestParam long id) {
        bookService.delete(id);
        return "redirect:/books";
    }
}
