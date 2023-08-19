package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.data.dto.BookDto;
import com.belhard.bookstore.data.entity.Book;
import com.belhard.bookstore.data.repository.BookRepository;
import com.belhard.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public List<BookDto> getAll() {
        log.debug("Service method running");
        return bookRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public BookDto getById(long id) {
        log.debug("Service method running");
        Book book = bookRepository.find(id);
        if (book == null) {
            throw new RuntimeException("Book with id:" + id + "not found");
        }
        return toDto(book);
    }


    @Override
    public BookDto create(BookDto bookDto) {
        log.debug("Service method running");
        if (bookDto.getIsbn() == null) {
            throw new RuntimeException();
        }
        Book entity = toEntity(bookDto);
        Book created = bookRepository.create(entity);
        return toDto(created);
    }

    @Override
    public BookDto update(BookDto bookDto) {
        log.debug("Service method running");
        if (bookDto.getIsbn() == null) {
            throw new RuntimeException();
        }
        Book entity = toEntity(bookDto);
        Book updated = bookRepository.update(entity);
        return toDto(updated);
    }

    @Override
    public void delete(long id) {
        log.debug("Service method running");
        if (!bookRepository.delete(id)) {
            throw new RuntimeException("Not found book with id: " + id);
        }
    }

    private BookDto toDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setTitle(book.getTitle());
        bookDto.setYear(book.getYear());
        bookDto.setPrice(book.getPrice());
        bookDto.setPages(book.getPages());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setCoverDto(BookDto.CoverDto.valueOf(book.getCover().toString()));
        return bookDto;
    }

    private Book toEntity(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setAuthor(bookDto.getAuthor());
        book.setTitle(bookDto.getTitle());
        book.setYear(bookDto.getYear());
        book.setPrice(bookDto.getPrice());
        book.setPages(bookDto.getPages());
        book.setIsbn(bookDto.getIsbn());
        book.setCover(Book.Cover.valueOf(bookDto.getCoverDto().toString()));
        return book;
    }
}
