package com.perficient.orderapp.domain.exception;

public class InvalidProductItemException extends Exception {

    public InvalidProductItemException(String errorMessage) {
        super(errorMessage);
    }
}
