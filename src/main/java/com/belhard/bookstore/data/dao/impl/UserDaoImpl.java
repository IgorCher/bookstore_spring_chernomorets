package com.belhard.bookstore.data.dao.impl;

import com.belhard.bookstore.data.dao.UserDao;
import com.belhard.bookstore.data.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Log4j2
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    private static final String FIND_BY_ID = "SELECT u.id, u.name, u.last_name, u.email, login, password, r.role FROM users u JOIN roles r ON u.role_id = r.id WHERE u.id = ?";
    private static final String FIND_ALL = "SELECT u.id, u.name, u.last_name, u.email, login, password, r.role FROM users u JOIN roles r ON u.role_id = r.id";
    private static final String FIND_BY_LAST_NAME = "SELECT u.id, u.name, u.last_name, u.email, login, password, r.role FROM users u JOIN roles r ON u.role_id = r.id WHERE u.last_name = ?";
    private static final String FIND_BY_EMAIL = "SELECT u.id, u.name, u.last_name, u.email, login, password, r.role FROM users u JOIN roles r ON u.role_id = r.id WHERE u.email = ?";
    private static final String CREATE = "INSERT INTO users (name, last_name, email, login, password, role_id) Values (:name, :lastName, :email, :login, :password, (SELECT id FROM roles WHERE role = :role))";
    private static final String UPDATE = "UPDATE users SET name = :name, last_name = :lastName, email = :email, login = :login, password = :password, role_id = (SELECT id FROM roles WHERE role = :role) WHERE id = :id";
    private static final String COUNT = "SELECT COUNT(u.id) FROM users u";
    private static final String DELETE = "DELETE FROM users WHERE id = ?";
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
        Map<String, Object> params = prepareForUpdate(user);
        SqlParameterSource namedParameters = new MapSqlParameterSource(params);
        namedParameterJdbcTemplate.update(CREATE, namedParameters, keyHolder, new String[] {"id"});
        long id = keyHolder.getKey().longValue();
        return find(id);
    }

    @Override
    public User update(User user) {
        Map<String, Object> params = prepareForUpdate(user);
        SqlParameterSource namedParameters = new MapSqlParameterSource(params);
        namedParameterJdbcTemplate.update(UPDATE,namedParameters);
        return find(user.getId());
    }

    @Override
    public boolean delete(long id) {
        return jdbcTemplate.update(DELETE, id) == 1;
    }

    @Override
    public long countAll() {
        return jdbcTemplate.queryForObject(COUNT, Long.class);
    }

    private static Map<String, Object> prepareForUpdate(User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", user.getId());
        params.put("name", user.getName());
        params.put("lastName", user.getLastName());
        params.put("email", user.getEmail());
        params.put("login", user.getLogin());
        params.put("password", user.getPassword());
        params.put("role", user.getRole().toString());
        return params;
    }
}
