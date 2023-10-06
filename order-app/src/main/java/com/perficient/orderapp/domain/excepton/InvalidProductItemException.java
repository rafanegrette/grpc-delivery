package com.perficient.orderapp.domain.excepton;

public class InvalidProductItemException extends RuntimeException {

    public InvalidProductItemException(String errorMessage) {
        super(errorMessage);
    }
}
