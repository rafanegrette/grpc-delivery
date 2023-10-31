package com.perficient.orderapp.domain.excepton;

public class EmptyCartException extends RuntimeException {


    public EmptyCartException() {
        super("The customer cart can't be empty.");
    }
}
