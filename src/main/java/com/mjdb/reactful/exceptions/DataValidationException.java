package com.mjdb.reactful.exceptions;

public class DataValidationException extends RuntimeException {
    public DataValidationException(String message) {
        super(message);
    }
}