package com.belhard.bookstore.service.exeption;

public class ResourceNotFoundException extends AppException {
    public ResourceNotFoundException (String message) {
        super(message);
    }

    public ResourceNotFoundException (String message, Throwable cause){
        super(message, cause);
    }
}