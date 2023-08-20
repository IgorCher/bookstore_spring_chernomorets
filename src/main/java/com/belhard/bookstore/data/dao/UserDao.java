package com.belhard.bookstore.data.dao;

import com.belhard.bookstore.data.dto.UserDto;

import java.util.List;

public interface UserDao {
    UserDto find(long id);

    List<UserDto> findAll();

    List<UserDto> findByLastName(String lastName);

    UserDto findByEmail(String email);

    UserDto create(UserDto userDto);

    UserDto update(UserDto userDto);

    boolean delete(long id);

    long countAll();

}
