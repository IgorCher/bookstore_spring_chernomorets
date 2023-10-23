package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.data.entity.User;
import com.belhard.bookstore.data.repository.UserRepository;
import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.dto.UserDto;
import com.belhard.bookstore.service.exeption.ResourceNotFoundException;
import com.belhard.bookstore.service.exeption.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper mapper;

    @Override
    public UserDto getById(long id) {
        log.debug("Service method running");
        return userRepository.find(id)
                .map(u -> mapper.map(u, UserDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " not found"));
    }

    @Override
    public UserDto getByEmail(String email) {
        log.debug("Service method running");
        return userRepository.findByEmail(email)
                .map(u -> mapper.map(u, UserDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + email + " not found"));
    }

    @Override
    public UserDto login(String login, String password) {
        log.debug("Service method running");
        return userRepository.findAll().stream()
                .map(u -> mapper.map(u, UserDto.class))
                .filter(user -> user.getLogin().equals(login) && user.getPassword().equals(password))
                .findFirst()
                .orElseThrow(() -> new SecurityException("Incorrect login or password!"));
    }

    @Override
    public List<UserDto> getAll() {
        log.debug("Service method running");
        return userRepository.findAll()
                .stream()
                .map(u -> mapper.map(u, UserDto.class))
                .toList();
    }

    @Override
    public UserDto update(UserDto userDto) {
        log.debug("Service method running");
        try {
            if (!userDto.getEmail().matches("^.*@.*\\..*$")) {
                throw new ValidationException("Invalid information");
            } else if (getByEmail(userDto.getEmail()).getEmail().equals(userDto.getEmail())) {
                throw new ValidationException("User with email: " + userDto.getEmail() + " already exists, try again!");
            }
            User entity = mapper.map(userDto, User.class);
            User updated = userRepository.save(entity);
            return mapper.map(updated, UserDto.class);
        } catch (ResourceNotFoundException e) {
            User entity = mapper.map(userDto, User.class);
            User updated = userRepository.save(entity);
            return mapper.map(updated, UserDto.class);
        }
    }

    @Override
    public void delete(long id) {
        log.debug("Service method running");
        if (!userRepository.delete(id)) {
            throw new ResourceNotFoundException("User with id: " + id + "not found");
        }
    }
}
