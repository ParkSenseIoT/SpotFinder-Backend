package com.spotfinderbackend.payments.domain.model.exceptions;

import com.spotfinderbackend.shared.domain.model.exceptions.BusinessRuleException;

public class PaymentFailedException extends BusinessRuleException {

    public PaymentFailedException() {
        super("Payment processing failed");
    }
}