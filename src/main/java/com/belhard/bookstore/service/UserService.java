package com.belhard.bookstore.service;

import com.belhard.bookstore.service.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto getById(long id);
    UserDto login(String login, String password);

    List<UserDto> getAll();

    UserDto create(UserDto userDto);

    UserDto update(UserDto userDto);

    void delete(long id);

}
