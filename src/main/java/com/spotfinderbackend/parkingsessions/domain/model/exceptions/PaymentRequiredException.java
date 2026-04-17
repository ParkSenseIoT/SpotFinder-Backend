package com.spotfinderbackend.parkingsessions.domain.model.exceptions;

public class PaymentRequiredException extends RuntimeException {
    public PaymentRequiredException() {
        super("Payment required to end the session");
    }
}