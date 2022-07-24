package com.reviewPointMangeServer.exception;

public class WriterAlreadyExistsException extends RuntimeException{
    public WriterAlreadyExistsException() {

    }
    public WriterAlreadyExistsException(String message) {
        super(message);
    }
    public WriterAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
