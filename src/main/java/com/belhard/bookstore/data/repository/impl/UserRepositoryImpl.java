package com.belhard.bookstore.data.repository.impl;

import com.belhard.bookstore.data.entity.User;
import com.belhard.bookstore.data.repository.UserRepository;
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
public class UserRepositoryImpl implements UserRepository {
    private static final String GET_ALL = "from User";
    private static final String GET_BY_LAST_NAME = "from User u where u.lastName = :lastName";
    private static final String COUNT = "select count(u.id) from User u";
    @PersistenceContext
    private EntityManager manager;

    @Override
    public User find(Long id) {
        return manager.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        return manager.createQuery(GET_ALL, User.class).getResultList();
    }

    @Override
    public List<User> findByLastName(String lastName) {
        TypedQuery<User> q = manager.createQuery(GET_BY_LAST_NAME, User.class);
        q.setParameter("lastName", lastName);
        return q.getResultList();
    }

    @Override
    public User findByEmail(String email) {
        return manager.find(User.class, email);
    }

    @Override
    public User save(User user) {
        if (user.getId() != null) {
            manager.merge(user);
        } else {
            manager.persist(user);
        }
        return user;
    }

    @Override
    public boolean delete(Long id) {
        User user = manager.find(User.class, id);
        if (user != null) {
            manager.remove(user);
            return true;
        }
        return false;
    }

    @Override
    public long countAll() {
        TypedQuery<Long> q = manager.createQuery(COUNT, Long.class);
        return q.getSingleResult();
    }
}
