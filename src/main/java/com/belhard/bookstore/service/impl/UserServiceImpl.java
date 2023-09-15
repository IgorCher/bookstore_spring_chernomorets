package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.data.entity.User;
import com.belhard.bookstore.data.repository.UserRepository;
import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.dto.UserDto;
import com.belhard.bookstore.service.exeption.AppException;
import com.belhard.bookstore.service.exeption.ResourceNotFoundException;
import com.belhard.bookstore.service.mapper.DataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final DataMapper dataMapper;

    @Override
    public UserDto getById(long id) {
        log.debug("Service method running");
        return userRepository.find(id)
                .map(dataMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " not found"));
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
            throw new AppException("Invalid information");
        }
        User entity = dataMapper.toEntity(userDto);
        User created = userRepository.save(entity);
        return dataMapper.toDto(created);
    }

    @Override
    public UserDto update(UserDto userDto) {
        log.debug("Service method running");
        if (userDto.getEmail() == null && userDto.getLogin() == null && userDto.getPassword() == null) {
            throw new AppException("Invalid information");
        }
        User entity = dataMapper.toEntity(userDto);
        User updated = userRepository.save(entity);
        return dataMapper.toDto(updated);
    }

    @Override
    public void delete(long id) {
        log.debug("Service method running");
        if (!userRepository.delete(id)) {
            throw new ResourceNotFoundException("User with id: " + id + "not found");
        }
    }
}
