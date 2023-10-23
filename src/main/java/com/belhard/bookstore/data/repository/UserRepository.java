package com.belhard.bookstore.data.repository;

import com.belhard.bookstore.data.entity.User;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
public interface UserRepository {

    Optional<User> find(Long id);

    List<User> findAll();

    List<User> findByLastName(String lastName);

    Optional<User> findByEmail(String email);

    User save(User user);

    boolean delete(Long id);

    long countAll();
}
