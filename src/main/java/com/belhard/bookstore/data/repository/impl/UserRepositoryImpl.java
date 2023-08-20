package com.belhard.bookstore.data.repository.impl;

import com.belhard.bookstore.data.dao.UserDao;
import com.belhard.bookstore.data.entity.User;
import com.belhard.bookstore.data.mapper.DataMapper;
import com.belhard.bookstore.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserDao userDao;
    private final DataMapper dataMapper;

    @Override
    public User find(Long id) {
        return dataMapper.toEntity(userDao.find(id));
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll()
                .stream()
                .map(dataMapper::toEntity)
                .toList();
    }

    @Override
    public List<User> findByLastName(String lastName) {
        return userDao.findByLastName(lastName)
                .stream()
                .map(dataMapper::toEntity)
                .toList();
    }

    @Override
    public User findByEmail(String email) {
        return dataMapper.toEntity(userDao.findByEmail(email));
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return userDao.delete(id);
    }

    @Override
    public long countAll() {
        return userDao.countAll();
    }
}
