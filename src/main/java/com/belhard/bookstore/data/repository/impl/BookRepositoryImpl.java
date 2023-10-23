package com.belhard.bookstore.data.repository.impl;


import com.belhard.bookstore.data.entity.Book;
import com.belhard.bookstore.data.repository.BookRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
@Repository
@RequiredArgsConstructor
@Transactional
public class BookRepositoryImpl implements BookRepository {

    private static final String FIND = "from Book b where b.id = :id and b.deleted = false";

    private static final String FIND_ALL = "from Book b where b.deleted = false";

    private static final String FIND_BY_AUTHOR = "from Book b where b.author = :author and b.deleted = false";

    private static final String FIND_BY_ISBN = "from Book b where b.isbn = :isbn and b.deleted = false";

    private static final String DELETE = "update Book b set deleted = true where b.id = :id";

    private static final String COUNT = "select count(b.id) from Book b where b.deleted = false";

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Optional<Book> find(Long id) {
        try {
            Book book = manager.createQuery(FIND, Book.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(book);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Book> findAll() {
        return manager.createQuery(FIND_ALL, Book.class).getResultList();
    }

    @Override
    public List<Book> findByAuthor(String author) {
        TypedQuery<Book> q = manager.createQuery(FIND_BY_AUTHOR, Book.class);
        q.setParameter("author", author);
        return q.getResultList();
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        try {
            Book book = manager.createQuery(FIND_BY_ISBN, Book.class)
                    .setParameter("isbn", isbn)
                    .getSingleResult();
            return Optional.of(book);
        } catch (NoResultException e) {
            return Optional.empty();
        }
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
            manager.createQuery(DELETE)
                    .setParameter("id", id)
                    .executeUpdate();
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
