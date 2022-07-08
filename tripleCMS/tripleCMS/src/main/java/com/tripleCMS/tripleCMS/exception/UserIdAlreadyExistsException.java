package com.tripleCMS.tripleCMS.exception;

public class UserIdAlreadyExistsException extends RuntimeException{
    public UserIdAlreadyExistsException() {

    }
    public UserIdAlreadyExistsException(String message) {
        super(message);
    }
    public UserIdAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
