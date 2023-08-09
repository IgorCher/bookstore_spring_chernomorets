package com.belhard.bookstore.web;

import jakarta.servlet.http.HttpServletRequest;

public interface Command {
    String process(HttpServletRequest req);
}
