package com.belhard.bookstore.data.repository;

import com.belhard.bookstore.data.entity.User;

import java.util.List;

public interface UserRepository {

    User find(Long id);

    List<User> findAll();

    List<User> findByLastName(String lastName);

    User findByEmail(String email);

    User save(User user);

    boolean delete(Long id);

    long countAll();
}
