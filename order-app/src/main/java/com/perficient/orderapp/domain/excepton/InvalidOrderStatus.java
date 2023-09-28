package com.perficient.orderapp.domain.excepton;

public class InvalidOrderStatus extends RuntimeException{

    public InvalidOrderStatus(String message) {
        super(message);
    }


}
