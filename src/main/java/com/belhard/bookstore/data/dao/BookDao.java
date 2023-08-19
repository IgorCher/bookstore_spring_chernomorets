package com.belhard.bookstore.data.dao;

import com.belhard.bookstore.data.dto.BookDto;

import java.util.List;

public interface BookDao {
    List<BookDto> findAll();

    List<BookDto> findByAuthor(String author);

    BookDto find(long id);

    BookDto findByIsbn(String isbn);

    BookDto create(BookDto bookDto);

    BookDto update(BookDto bookDto);

    boolean delete(long id);

    long countAll();

}
