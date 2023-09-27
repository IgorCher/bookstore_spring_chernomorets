package com.belhard.bookstore.web.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = {"/users/*", "/books/*", "/orders/*"})
public class AuthorizationFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (!req.getRequestURI().equals("/books") & !req.getRequestURI().equals("/users/create")) {
            if (req.getSession().getAttribute("user") == null) {
                res.sendRedirect("/login");
                return;
            }
        }
        chain.doFilter(req, res);
    }
}
