package com.example.V_RGUIDE.exception;

// Custom Exception for our project
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}