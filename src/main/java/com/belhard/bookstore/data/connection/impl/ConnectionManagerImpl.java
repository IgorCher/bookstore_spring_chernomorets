package com.belhard.bookstore.data.connection.impl;

import com.belhard.bookstore.data.connection.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class ConnectionManagerImpl implements Closeable, ConnectionManager {
    @Value("${db.url}")
    private String url;
    @Value("${db.login}")
    private String login;
    @Value("${db.password}")
    private String password;
    @Value("${db.driver}")
    private String driver;
    private Connection connection;

    @Override
    public Connection getConnection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, login, password);
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
