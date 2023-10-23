package com.belhard.bookstore.data.repository.impl;

import com.belhard.bookstore.data.entity.User;
import com.belhard.bookstore.data.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
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
public class UserRepositoryImpl implements UserRepository {

    private static final String FIND = "from User u where u.id = :id and u.deleted = false";

    private static final String FIND_ALL = "from User u where u.deleted = false";

    private static final String FIND_BY_LAST_NAME = "from User u where u.lastName = :lastName and u.deleted = false";

    private static final String FIND_BY_EMAIL = "from User u where u.email = :email and u.deleted = false";

    private static final String DELETE = "update User u set deleted = true where u.id = :id";

    private static final String COUNT = "select count(u.id) from User u";

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Optional<User> find(Long id) {
        try {
            User user = manager.createQuery(FIND, User.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(user);
        } catch (EntityNotFoundException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        return manager.createQuery(FIND_ALL, User.class).getResultList();
    }

    @Override
    public List<User> findByLastName(String lastName) {
        TypedQuery<User> q = manager.createQuery(FIND_BY_LAST_NAME, User.class);
        q.setParameter("lastName", lastName);
        return q.getResultList();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            User user = manager.createQuery(FIND_BY_EMAIL, User.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return Optional.of(user);
        } catch (NoResultException e) {
            return Optional.empty();
        }
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
            manager.createQuery(DELETE)
                    .setParameter("id", id)
                    .executeUpdate();
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
