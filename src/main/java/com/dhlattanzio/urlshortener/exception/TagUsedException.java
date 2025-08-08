package com.dhlattanzio.urlshortener.exception;

public class TagUsedException extends RuntimeException {
    public TagUsedException(String message) {
        super(message);
    }
}
