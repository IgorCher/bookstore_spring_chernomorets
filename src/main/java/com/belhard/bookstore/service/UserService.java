package com.belhard.bookstore.service;

import com.belhard.bookstore.service.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto getById(long id);

    UserDto getByEmail(String email);

    UserDto login(String login, String password);

    List<UserDto> getAll();

    UserDto update(UserDto userDto);

    void delete(long id);

}
