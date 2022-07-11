package com.tripleCMS.tripleCMS.exception;

public class ReviewNotFoundException extends RuntimeException{

    public ReviewNotFoundException() {
    }

    public ReviewNotFoundException(String message) {
        super(message);
    }

    public ReviewNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
