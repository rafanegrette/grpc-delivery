package com.perficient.application.error;

public class MenuNotFound extends RuntimeException {

    public MenuNotFound(final String message) {
        super(message);
    }
}
