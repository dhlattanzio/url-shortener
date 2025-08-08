package com.dhlattanzio.urlshortener.exception;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException(String message) {
        super(message);
    }
}
