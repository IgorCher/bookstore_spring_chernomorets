package com.belhard.bookstore.data.dao;

import com.belhard.bookstore.data.entity.User;

import java.util.List;

public interface UserDao {
    User find(long id);

    List<User> findAll();

    List<User> findByLastName(String lastName);

    User findByEmail(String email);

    User create(User user);

    User update(User user);

    boolean delete(long id);

    long countAll();

}
