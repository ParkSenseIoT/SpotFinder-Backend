package com.spotfinderbackend.accesscontrol.domain.model.exceptions;

import com.spotfinderbackend.shared.domain.model.exceptions.BusinessRuleException;

public class PaymentRequiredException extends BusinessRuleException {

    public PaymentRequiredException(Long sessionId) {
        super("Payment required for session " + sessionId);
    }
}