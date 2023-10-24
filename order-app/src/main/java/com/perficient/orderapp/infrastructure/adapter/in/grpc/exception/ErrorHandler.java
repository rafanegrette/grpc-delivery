package com.perficient.orderapp.infrastructure.adapter.in.grpc.exception;

import com.perficient.orderapp.domain.excepton.ProductNotFoundException;
import com.perficient.orderapp.domain.excepton.UnavailablePaymentException;
import io.grpc.Status;
import org.lognet.springboot.grpc.recovery.GRpcExceptionHandler;
import org.lognet.springboot.grpc.recovery.GRpcExceptionScope;
import org.lognet.springboot.grpc.recovery.GRpcServiceAdvice;

@GRpcServiceAdvice
public class ErrorHandler {

    @GRpcExceptionHandler
    public Status handle(ProductNotFoundException ex, GRpcExceptionScope scope) {
        return Status.NOT_FOUND.withDescription("Product Not found");
    }

    @GRpcExceptionHandler
    public Status handle(UnavailablePaymentException ex, GRpcExceptionScope scope) {
        return Status.UNAVAILABLE
                .withCause(new UnavailablePaymentException())
                .withDescription("The payment application is not available, contact support team");

    }
}
