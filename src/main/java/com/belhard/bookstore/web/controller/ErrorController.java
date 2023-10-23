package com.belhard.bookstore.web.controller;

import com.belhard.bookstore.service.exeption.AppException;
import com.belhard.bookstore.service.exeption.ResourceNotFoundException;
import com.belhard.bookstore.service.exeption.ValidationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Log4j2
public class ErrorController {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String processError(RuntimeException e, Model model) {
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        log.error(e.getMessage(), e);
        return "error";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String dataError(ResourceNotFoundException e, Model model) {
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());
        model.addAttribute("message", e.getMessage());
        log.error(e.getMessage(), e);
        return "error";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String appError(AppException e, Model model) {
        model.addAttribute("status", HttpStatus.BAD_REQUEST.value());
        model.addAttribute("message", e.getMessage());
        log.error(e.getMessage(), e);
        return "error";
    }

    @ExceptionHandler
    public String validationError(ValidationException e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "error";
    }

    @ExceptionHandler
    public String loginError(SecurityException e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "loginForm";
    }
}
