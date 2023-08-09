package com.belhard.bookstore.web;

import com.belhard.bookstore.data.connection.ConnectionManager;
import com.belhard.bookstore.data.connection.impl.ConnectionManagerImpl;
import com.belhard.bookstore.data.dao.BookDao;
import com.belhard.bookstore.data.dao.UserDao;
import com.belhard.bookstore.data.dao.impl.BookDaoImpl;
import com.belhard.bookstore.data.dao.impl.UserDaoImpl;
import com.belhard.bookstore.platform.ConfigurationManager;
import com.belhard.bookstore.platform.impl.ConfigurationManagerImpl;
import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.UserService;
import com.belhard.bookstore.service.impl.BookServiceImpl;
import com.belhard.bookstore.service.impl.UserServiceImpl;
import lombok.extern.log4j.Log4j2;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class ControllerFactory implements Closeable {
    public static final ControllerFactory INSTANCE = new ControllerFactory();
    private final List<Closeable> resources;
    private Map<String, Command> controllers;

    public ControllerFactory() {
        resources = new ArrayList<>();
        controllers = new HashMap<>();
        ConfigurationManager configurationManager = new ConfigurationManagerImpl();
        String url = configurationManager.getProperty("db.url");
        String login = configurationManager.getProperty("db.login");
        String password = configurationManager.getProperty("db.password");
        String driver = configurationManager.getProperty("db.driver");

        ConnectionManager connectionManager = new ConnectionManagerImpl(url, login, password, driver);
        resources.add(connectionManager);
        UserDao userDao = new UserDaoImpl(connectionManager);
        UserService userService = new UserServiceImpl(userDao);

        BookDao bookDao = new BookDaoImpl(connectionManager);
        BookService bookService = new BookServiceImpl(bookDao);

        controllers.put("home", new HomeCommand());
        controllers.put("user", new UserCommand(userService));
        controllers.put("users", new UsersCommand(userService));
        controllers.put("book", new BookCommand(bookService));
        controllers.put("books", new BooksCommand(bookService));
        controllers.put("error", new ErrorCommand());
        controllers.put("create_user_form", new CreateUserFormCommand());
        controllers.put("create_user", new CreateUser(userService));
        controllers.put("create_book_form", new CreateBookFormCommand());
        controllers.put("create_book", new CreateBook(bookService));
        controllers.put("edit_user_form", new EditUserFormCommand(userService));
        controllers.put("edit_user", new EditUser(userService));
        controllers.put("edit_book_form", new EditBookFormCommand(bookService));
        controllers.put("edit_book", new EditBook(bookService));
        controllers.put("delete_user", new DeleteUserCommand(userService));
        controllers.put("delete_book", new DeleteBookCommand(bookService));
    }

    public Command getController(String command) {
        Command controller = controllers.get(command);
        if (controller == null) {
            controller = controllers.get("error");
        }
        return controller;
    }

    @Override
    public void close() {
        for (Closeable resource : resources) {
            try {
                resource.close();
            } catch (IOException e) {
                log.error(e);
            }
        }
    }
}
