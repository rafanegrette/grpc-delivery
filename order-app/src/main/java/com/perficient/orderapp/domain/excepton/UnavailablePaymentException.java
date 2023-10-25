package com.perficient.orderapp.domain.excepton;

public class UnavailablePaymentException extends RuntimeException{

    public UnavailablePaymentException() {
        super("Payment Application is not available, contact Payment Support Team");
    }
}
