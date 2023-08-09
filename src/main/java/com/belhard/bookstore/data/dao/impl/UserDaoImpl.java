package com.belhard.bookstore.data.dao.impl;

import com.belhard.bookstore.data.connection.ConnectionManager;
import com.belhard.bookstore.data.dao.UserDao;
import com.belhard.bookstore.data.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    private final ConnectionManager connectionManager;

    public static User mapRow(ResultSet resultSet) throws SQLException {
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
        try (Connection connection = connectionManager.getConnection()) {
            log.info("Connected to database");
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            log.debug("SQL query");
            if (resultSet.next()) {
                return mapRow(resultSet);
            }
        } catch (SQLException e) {
            log.error("Connection failed");
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection()) {
            log.info("Connected to database");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            log.debug("SQL query");
            while (resultSet.next()) {
                users.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            log.error("Connection failed");
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public List<User> findByLastName(String lastName) {
        List<User> users = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection()) {
            log.info("Connected to database");
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_LAST_NAME);
            preparedStatement.setString(1, lastName);
            ResultSet resultSet = preparedStatement.executeQuery();
            log.debug("SQL query");
            while (resultSet.next()) {
                users.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            log.error("Connection failed");
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public User findByEmail(String email) {
        try (Connection connection = connectionManager.getConnection()) {
            log.info("Connected to database");
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            log.debug("SQL query");
            if (resultSet.next()) {
                return mapRow(resultSet);
            }
        } catch (SQLException e) {
            log.error("Connection failed");
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public User create(User user) {
        try (Connection connection = connectionManager.getConnection()) {
            log.info("Connected to database");
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getRole().toString());

            preparedStatement.executeUpdate();
            log.debug("SQL query");

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                long id = generatedKeys.getLong("id");
                return find(id);
            }
        } catch (SQLException e) {
            log.error("Connection failed");
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public User update(User user) {
        try (Connection connection = connectionManager.getConnection()) {
            log.info("Connected to database");
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getRole().toString());
            preparedStatement.setLong(7, user.getId());

            preparedStatement.executeUpdate();
            log.debug("SQL query");

        } catch (SQLException e) {
            log.error("Connection failed");
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public boolean delete(long id) {
        try (Connection connection = connectionManager.getConnection()) {
            log.info("Connected to database");
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setLong(1, id);
            int rows = preparedStatement.executeUpdate();
            log.debug("SQL query");
            return rows == 1;
        } catch (SQLException e) {
            log.error("Connection failed");
            throw new RuntimeException(e);
        }
    }

    @Override
    public long countAll() {
        try (Connection connection = connectionManager.getConnection()) {
            log.info("Connected to database");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(COUNT);
            log.debug("SQL query");

            if (resultSet.next()) {
                return resultSet.getLong("count");
            }
        } catch (SQLException e) {
            log.error("Connection failed");
            throw new RuntimeException(e);
        }
        return 0;
    }
}
