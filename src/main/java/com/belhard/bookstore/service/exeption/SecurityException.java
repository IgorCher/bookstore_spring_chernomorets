package com.belhard.bookstore.service.exeption;

@SuppressWarnings("unused")
public class SecurityException extends AppException{

    public SecurityException(String message) {
        super(message);
    }

    public SecurityException(String message, Throwable cause) {
        super(message, cause);
    }
}
