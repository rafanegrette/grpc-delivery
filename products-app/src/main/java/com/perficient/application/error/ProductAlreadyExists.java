package com.perficient.application.error;

public class ProductAlreadyExists extends RuntimeException {

    public ProductAlreadyExists(final String message) {
        super(message);
    }

}
