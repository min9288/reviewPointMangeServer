package com.reviewPointMangeServer.reviewPointMangeServer.exception;

public class PlaceAlreadyExistsException extends RuntimeException{
    public PlaceAlreadyExistsException() {

    }
    public PlaceAlreadyExistsException(String message) {
        super(message);
    }
    public PlaceAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
