package com.spotfinderbackend.payments.domain.model.exceptions;

import com.spotfinderbackend.shared.domain.model.exceptions.BusinessRuleException;

public class InvalidPaymentException extends BusinessRuleException {

    public InvalidPaymentException(String message) {
        super(message);
    }
}