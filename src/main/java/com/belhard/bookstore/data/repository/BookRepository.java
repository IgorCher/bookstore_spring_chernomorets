package com.belhard.bookstore.data.repository;

import com.belhard.bookstore.data.entity.Book;

import java.util.List;

public interface BookRepository {

    Book find(long id);

    List<Book> findAll();

    List<Book> findByAuthor(String author);

    Book findByIsbn(String isbn);

    Book create(Book entity);

    Book update(Book entity);

    boolean delete(long id);

    long countAll();
}
