package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.data.entity.Book;
import com.belhard.bookstore.data.repository.BookRepository;
import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.dto.BookDto;
import com.belhard.bookstore.service.exeption.ResourceNotFoundException;
import com.belhard.bookstore.service.exeption.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final ModelMapper mapper;

    @Override
    public List<BookDto> getAll() {
        log.debug("Service method running");
        return bookRepository.findAll()
                .stream()
                .map(b -> mapper.map(b, BookDto.class))
                .toList();
    }

    @Override
    public BookDto getById(long id) {
        log.debug("Service method running");
        return bookRepository.find(id)
                .map(b -> mapper.map(b, BookDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("Book with id: " + id + " not found"));
    }

    @Override
    public BookDto getByIsbn(String isbn) {
        log.debug("Service method running");
        return bookRepository.findByIsbn(isbn)
                .map(b -> mapper.map(b, BookDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("Book with isbn: " + isbn + " not found"));
    }

    @Override
    public BookDto update(BookDto bookDto) {
        log.debug("Service method running");
        try {
            if (!bookDto.getIsbn().matches("[0-9]{13}")) {
                throw new ValidationException("Invalid information");
            } else if (getByIsbn(bookDto.getIsbn()).getIsbn().equals(bookDto.getIsbn())) {
                throw new ValidationException("The book with isbn: " + bookDto.getIsbn() + " already exists, try again!");
            }
            Book entity = mapper.map(bookDto, Book.class);
            Book updated = bookRepository.save(entity);
            return mapper.map(updated, BookDto.class);
        } catch (ResourceNotFoundException e) {
            Book entity = mapper.map(bookDto, Book.class);
            Book updated = bookRepository.save(entity);
            return mapper.map(updated, BookDto.class);
        }
    }

    @Override
    public void delete(long id) {
        log.debug("Service method running");
        if (!bookRepository.delete(id)) {
            throw new ResourceNotFoundException("Not found book with id: " + id);
        }
    }
}
