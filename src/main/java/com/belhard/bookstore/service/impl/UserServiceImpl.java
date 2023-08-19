package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.data.dto.UserDto;
import com.belhard.bookstore.data.entity.User;
import com.belhard.bookstore.data.repository.UserRepository;
import com.belhard.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDto getById(long id) {
        log.debug("Service method running");
        User user = userRepository.find(id);
        if (user == null) {
            throw new RuntimeException("User with id: " + id + "not found");
        }
        return toDto(user);
    }

    @Override
    public List<UserDto> getAll() {
        log.debug("Service method running");
        return userRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public UserDto create(UserDto userDto) {
        log.debug("Service method running");
        if (userDto.getEmail() == null && userDto.getLogin() == null && userDto.getPassword() == null) {
            throw new RuntimeException("Invalid information");
        }
        User entity = toEntity(userDto);
        User created = userRepository.create(entity);
        return toDto(created);
    }

    @Override
    public UserDto update(UserDto userDto) {
        log.debug("Service method running");
        if (userDto.getEmail() == null && userDto.getLogin() == null && userDto.getPassword() == null) {
            throw new RuntimeException("Invalid information");
        }
        User entity = toEntity(userDto);
        User updated = userRepository.update(entity);
        return toDto(updated);
    }

    @Override
    public void delete(long id) {
        log.debug("Service method running");
        if (!userRepository.delete(id)) {
            throw new RuntimeException("User with id: " + id + "not found");
        }
    }

    private UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setRoleDto(UserDto.RoleDto.valueOf(user.getRole().toString()));
        return userDto;
    }

    private User toEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setRole(User.Role.valueOf(userDto.getRoleDto().toString()));
        return user;
    }
}
