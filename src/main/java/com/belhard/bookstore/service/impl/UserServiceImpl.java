package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.data.entity.User;
import com.belhard.bookstore.data.repository.UserRepository;
import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.dto.UserDto;
import com.belhard.bookstore.service.mapper.DataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final DataMapper dataMapper;

    @Override
    public UserDto getById(long id) {
        log.debug("Service method running");
        User user = userRepository.find(id);
        if (user == null) {
            throw new RuntimeException("User with id: " + id + "not found");
        }
        return dataMapper.toDto(user);
    }

    @Override
    public List<UserDto> getAll() {
        log.debug("Service method running");
        return userRepository.findAll()
                .stream()
                .map(dataMapper::toDto)
                .toList();
    }

    @Override
    public UserDto create(UserDto userDto) {
        log.debug("Service method running");
        if (userDto.getEmail() == null && userDto.getLogin() == null && userDto.getPassword() == null) {
            throw new RuntimeException("Invalid information");
        }
        User entity = dataMapper.toEntity(userDto);
        User created = userRepository.save(entity);
        return dataMapper.toDto(created);
    }

    @Override
    public UserDto update(UserDto userDto) {
        log.debug("Service method running");
        if (userDto.getEmail() == null && userDto.getLogin() == null && userDto.getPassword() == null) {
            throw new RuntimeException("Invalid information");
        }
        User entity = dataMapper.toEntity(userDto);
        User updated = userRepository.save(entity);
        return dataMapper.toDto(updated);
    }

    @Override
    public void delete(long id) {
        log.debug("Service method running");
        if (!userRepository.delete(id)) {
            throw new RuntimeException("User with id: " + id + "not found");
        }
    }
}
