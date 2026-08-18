package com.avaj.exception;

public abstract class AvajException extends RuntimeException {
    protected AvajException(String message) {
        super(message);
    }
    protected AvajException(String message, Throwable cause) {
        super(message, cause);
    }
}
