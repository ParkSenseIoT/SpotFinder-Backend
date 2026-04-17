package com.spotfinderbackend.parkingsessions.domain.model.exceptions;

public class PaymentRequiredException extends RuntimeException {
    public PaymentRequiredException(String message) {
        super(message);
    }
}
