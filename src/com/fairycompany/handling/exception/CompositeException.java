package com.fairycompany.handling.exception;

public class CompositeException extends Throwable{
    public CompositeException() {
        super();
    }

    public CompositeException(String message) {
        super(message);
    }

    public CompositeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompositeException(Throwable cause) {
        super(cause);
    }
}
