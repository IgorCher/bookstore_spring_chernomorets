package com.belhard.bookstore.web;

import com.belhard.bookstore.service.exeption.AppException;
import com.belhard.bookstore.service.exeption.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@Log4j2
public class ErrorController {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String error(ResourceNotFoundException e, Model model) {
        model.addAttribute("status", 404);
        model.addAttribute("message", e.getMessage());
        log.error(e.getMessage(), e);
        return "error";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String error(AppException e, Model model) {
        model.addAttribute("status", 500);
        model.addAttribute("message", e.getMessage());
        log.error(e.getMessage(), e);
        return "error";
    }
}
