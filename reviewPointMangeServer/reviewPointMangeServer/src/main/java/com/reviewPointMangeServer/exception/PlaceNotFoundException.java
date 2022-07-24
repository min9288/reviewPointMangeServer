package com.reviewPointMangeServer.exception;

public class PlaceNotFoundException extends RuntimeException{
    public PlaceNotFoundException() {
    }

    public PlaceNotFoundException(String message) {
        super(message);
    }

    public PlaceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
