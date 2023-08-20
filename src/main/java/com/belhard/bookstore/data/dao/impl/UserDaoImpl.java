package com.belhard.bookstore.data.dao.impl;

import com.belhard.bookstore.data.dao.UserDao;
import com.belhard.bookstore.data.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

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
    private static final String FIND_ALL = "SELECT u.id, u.name, u.last_name, u.email, login, password, r.role FROM users u JOIN roles r ON u.role_id = r.id ORDER BY u.id";
    private static final String FIND_BY_LAST_NAME = "SELECT u.id, u.name, u.last_name, u.email, login, password, r.role FROM users u JOIN roles r ON u.role_id = r.id WHERE u.last_name = ? ORDER BY u.id";
    private static final String FIND_BY_EMAIL = "SELECT u.id, u.name, u.last_name, u.email, login, password, r.role FROM users u JOIN roles r ON u.role_id = r.id WHERE u.email = ?";
    private static final String CREATE = "INSERT INTO users (name, last_name, email, login, password, role_id) Values (:name, :lastName, :email, :login, :password, (SELECT id FROM roles WHERE role = :role))";
    private static final String UPDATE = "UPDATE users SET name = :name, last_name = :lastName, email = :email, login = :login, password = :password, role_id = (SELECT id FROM roles WHERE role = :role) WHERE id = :id";
    private static final String COUNT = "SELECT COUNT(u.id) FROM users u";
    private static final String DELETE = "DELETE FROM users WHERE id = ?";
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private UserDto mapRow(ResultSet resultSet, int rows) throws SQLException {
        UserDto userDto = new UserDto();
        userDto.setId(resultSet.getLong("id"));
        userDto.setName(resultSet.getString("name"));
        userDto.setLastName(resultSet.getString("last_name"));
        userDto.setEmail(resultSet.getString("email"));
        userDto.setLogin(resultSet.getString("login"));
        userDto.setPassword(resultSet.getString("password"));
        String roleRaw = resultSet.getString("role");
        userDto.setRoleDto(UserDto.RoleDto.valueOf(roleRaw));
        return userDto;
    }

    @Override
    public UserDto find(long id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, this::mapRow, id);
    }

    @Override
    public List<UserDto> findAll() {
        return jdbcTemplate.query(FIND_ALL, this::mapRow);
    }

    @Override
    public List<UserDto> findByLastName(String lastName) {
        return jdbcTemplate.query(FIND_BY_LAST_NAME, this::mapRow, lastName);
    }

    @Override
    public UserDto findByEmail(String email) {
        return jdbcTemplate.queryForObject(FIND_BY_EMAIL, this::mapRow, email);
    }

    @Override
    public UserDto create(UserDto userDto) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> params = prepareForUpdate(userDto);
        SqlParameterSource namedParameters = new MapSqlParameterSource(params);
        namedParameterJdbcTemplate.update(CREATE, namedParameters, keyHolder, new String[]{"id"});
        long id = keyHolder.getKey().longValue();
        return find(id);
    }

    @Override
    public UserDto update(UserDto userDto) {
        Map<String, Object> params = prepareForUpdate(userDto);
        SqlParameterSource namedParameters = new MapSqlParameterSource(params);
        namedParameterJdbcTemplate.update(UPDATE, namedParameters);
        return find(userDto.getId());
    }

    @Override
    public boolean delete(long id) {
        return jdbcTemplate.update(DELETE, id) == 1;
    }

    @Override
    public long countAll() {
        return jdbcTemplate.queryForObject(COUNT, Long.class);
    }

    private static Map<String, Object> prepareForUpdate(UserDto userDto) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", userDto.getId());
        params.put("name", userDto.getName());
        params.put("lastName", userDto.getLastName());
        params.put("email", userDto.getEmail());
        params.put("login", userDto.getLogin());
        params.put("password", userDto.getPassword());
        params.put("role", userDto.getRoleDto().toString());
        return params;
    }
}
