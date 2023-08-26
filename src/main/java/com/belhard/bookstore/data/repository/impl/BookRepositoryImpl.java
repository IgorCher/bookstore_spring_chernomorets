package com.belhard.bookstore.data.repository.impl;


import com.belhard.bookstore.data.entity.Book;
import com.belhard.bookstore.data.repository.BookRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class BookRepositoryImpl implements BookRepository {
    private static final String GET_ALL = "from Book";
    private static final String GET_BY_AUTHOR = "from Book b where b.author = :author";
    private static final String COUNT = "select count(b.id) from Book b";
    @PersistenceContext
    private EntityManager manager;

    @Override
    public Book find(Long id) {
        return manager.find(Book.class, id);
    }

    @Override
    public List<Book> findAll() {
        return manager.createQuery(GET_ALL, Book.class).getResultList();
    }

    @Override
    public List<Book> findByAuthor(String author) {
        TypedQuery<Book> q = manager.createQuery(GET_BY_AUTHOR, Book.class);
        q.setParameter("author", author);
        return q.getResultList();
    }

    @Override
    public Book findByIsbn(String isbn) {
        return manager.find(Book.class, isbn);
    }

    @Override
    public Book save(Book entity) {
        if (entity.getId() != null) {
            manager.merge(entity);
        } else {
            manager.persist(entity);
        }
        return entity;
    }

    @Override
    public boolean delete(Long id) {
        Book book = manager.find(Book.class, id);
        if (book != null) {
            manager.remove(book);
            return true;
        }
        return false;
    }

    @Override
    public Long countAll() {
        TypedQuery<Long> q = manager.createQuery(COUNT, Long.class);
        return q.getSingleResult();
    }
}
