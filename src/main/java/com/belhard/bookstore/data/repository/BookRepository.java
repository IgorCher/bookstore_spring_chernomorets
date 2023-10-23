package com.belhard.bookstore.data.repository;

import com.belhard.bookstore.data.entity.Book;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
public interface BookRepository {

    Optional<Book> find(Long id);

    List<Book> findAll();


    List<Book> findByAuthor(String author);

    Optional<Book> findByIsbn(String isbn);

    Book save(Book entity);

    boolean delete(Long id);

    Long countAll();
}
