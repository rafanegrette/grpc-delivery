package com.perficient.application.error;

public class RestaurantNotFound extends RuntimeException {

    public RestaurantNotFound(final String message) {
        super(message);
    }
}
