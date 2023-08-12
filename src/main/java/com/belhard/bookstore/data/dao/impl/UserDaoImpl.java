package com.belhard.bookstore.data.dao.impl;

import com.belhard.bookstore.data.dao.UserDao;
import com.belhard.bookstore.data.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Log4j2
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    private static final String FIND_BY_ID = "SELECT u.id, u.name, u.last_name, u.email, login, password, r.role FROM users u JOIN roles r ON u.role_id = r.id WHERE u.id = ?";
    private static final String FIND_ALL = "SELECT u.id, u.name, u.last_name, u.email, login, password, r.role FROM users u JOIN roles r ON u.role_id = r.id";
    private static final String FIND_BY_LAST_NAME = "SELECT u.id, u.name, u.last_name, u.email, login, password, r.role FROM users u JOIN roles r ON u.role_id = r.id WHERE u.last_name = ?";
    private static final String FIND_BY_EMAIL = "SELECT u.id, u.name, u.last_name, u.email, login, password, r.role FROM users u JOIN roles r ON u.role_id = r.id WHERE u.email = ?";
    private static final String CREATE = "INSERT INTO users (name, last_name, email, login, password, role_id) Values (?, ?, ?, ?, ?, (SELECT id FROM roles WHERE role = ?))";
    private static final String UPDATE = "UPDATE users SET name = ?, last_name = ?, email = ?, login = ?, password = ?, role_id = (SELECT id FROM roles WHERE role = ?) WHERE id = ?";
    private static final String COUNT = "SELECT COUNT(u.id) FROM users u";
    private static final String DELETE = "DELETE FROM users WHERE id = ?";
    private final JdbcTemplate jdbcTemplate;

    private User mapRow(ResultSet resultSet, int rows) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setEmail(resultSet.getString("email"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        String roleRaw = resultSet.getString("role");
        user.setRole(User.Role.valueOf(roleRaw));
        return user;
    }

    @Override
    public User find(long id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, this::mapRow, id);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(FIND_ALL, this::mapRow);
    }

    @Override
    public List<User> findByLastName(String lastName) {
        return jdbcTemplate.query(FIND_BY_LAST_NAME, this::mapRow, lastName);
    }

    @Override
    public User findByEmail(String email) {
        return jdbcTemplate.queryForObject(FIND_BY_EMAIL, this::mapRow, email);
    }

    @Override
    public User create(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement statement = conn.prepareStatement(CREATE);

            statement.setString(1, user.getName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getRole().toString());
            return statement;
        }, keyHolder);
        long id = keyHolder.getKey().longValue();
        return find(id);
    }

    @Override
    public User update(User user) {
        jdbcTemplate.update(conn -> {
            PreparedStatement statement = conn.prepareStatement(UPDATE);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getRole().toString());
            statement.setLong(7, user.getId());
            return statement;
        });
        return user;
    }

    @Override
    public boolean delete(long id) {
        return jdbcTemplate.update(DELETE, id) == 1;
    }

    @Override
    public long countAll() {
        return jdbcTemplate.queryForObject(COUNT, Long.class);
    }
}
