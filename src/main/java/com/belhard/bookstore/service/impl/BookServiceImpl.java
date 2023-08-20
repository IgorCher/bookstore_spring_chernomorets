package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.data.entity.Book;
import com.belhard.bookstore.data.repository.BookRepository;
import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.dto.BookDto;
import com.belhard.bookstore.service.mapper.DataMapperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final DataMapperService dataMapperService;

    @Override
    public List<BookDto> getAll() {
        log.debug("Service method running");
        return bookRepository.findAll()
                .stream()
                .map(dataMapperService::toDto)
                .toList();
    }

    @Override
    public BookDto getById(long id) {
        log.debug("Service method running");
        Book book = bookRepository.find(id);
        if (book == null) {
            throw new RuntimeException("Book with id:" + id + "not found");
        }
        return dataMapperService.toDto(book);
    }


    @Override
    public BookDto create(BookDto bookDto) {
        log.debug("Service method running");
        if (bookDto.getIsbn() == null) {
            throw new RuntimeException();
        }
        Book entity = dataMapperService.toEntity(bookDto);
        Book created = bookRepository.create(entity);
        return dataMapperService.toDto(created);
    }

    @Override
    public BookDto update(BookDto bookDto) {
        log.debug("Service method running");
        if (bookDto.getIsbn() == null) {
            throw new RuntimeException();
        }
        Book entity = dataMapperService.toEntity(bookDto);
        Book updated = bookRepository.update(entity);
        return dataMapperService.toDto(updated);
    }

    @Override
    public void delete(long id) {
        log.debug("Service method running");
        if (!bookRepository.delete(id)) {
            throw new RuntimeException("Not found book with id: " + id);
        }
    }
}
