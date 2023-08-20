package com.belhard.bookstore.data.repository.impl;


import com.belhard.bookstore.data.dao.BookDao;
import com.belhard.bookstore.data.dto.BookDto;
import com.belhard.bookstore.data.entity.Book;
import com.belhard.bookstore.data.mapper.DataMapper;
import com.belhard.bookstore.data.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {
    private final BookDao bookDao;
    private final DataMapper dataMapper;

    @Override
    public Book find(long id) {
        return dataMapper.toEntity(bookDao.find(id));
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll()
                .stream()
                .map(dataMapper::toEntity)
                .toList();
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return bookDao.findByAuthor(author)
                .stream()
                .map(dataMapper::toEntity)
                .toList();
    }

    @Override
    public Book findByIsbn(String isbn) {
        return dataMapper.toEntity(bookDao.findByIsbn(isbn));
    }

    @Override
    public Book create(Book entity) {
        BookDto bookDto = dataMapper.toDto(entity);
        BookDto created = bookDao.create(bookDto);
        return dataMapper.toEntity(created);
    }

    @Override
    public Book update(Book entity) {
        BookDto bookDto = dataMapper.toDto(entity);
        BookDto updated = bookDao.update(bookDto);
        return dataMapper.toEntity(updated);
    }

    @Override
    public boolean delete(long id) {
        return bookDao.delete(id);
    }

    @Override
    public long countAll() {
        return bookDao.countAll();
    }
}
