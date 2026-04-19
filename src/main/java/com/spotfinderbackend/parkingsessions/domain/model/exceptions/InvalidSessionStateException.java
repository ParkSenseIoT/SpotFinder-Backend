package com.spotfinderbackend.parkingsessions.domain.model.exceptions;

import com.spotfinderbackend.shared.domain.model.exceptions.BusinessRuleException;

public class InvalidSessionStateException extends BusinessRuleException {
    public InvalidSessionStateException(String message) {
        super(message);
    }
}