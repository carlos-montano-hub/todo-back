package com.encora.todoback.service.exceptions;

public class NullRequiredParameterException extends Exception {
    public NullRequiredParameterException(String message) {
        super(message);
    }
}
