package com.belhard.bookstore.data.repository;

import com.belhard.bookstore.data.entity.User;

import java.util.List;

public interface UserRepository {

    User find(long id);

    List<User> findAll();

    List<User> findByLastName(String lastName);

    User findByEmail(String email);

    User create(User user);

    User update(User user);

    boolean delete(long id);

    long countAll();
}
