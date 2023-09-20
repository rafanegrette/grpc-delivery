package com.perficient.application.error;

public class ProductNotFound extends RuntimeException {

    public ProductNotFound(final String message) {
        super(message);
    }
}
