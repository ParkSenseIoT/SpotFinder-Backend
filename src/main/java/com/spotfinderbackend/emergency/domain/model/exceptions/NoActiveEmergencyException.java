package com.spotfinderbackend.emergency.domain.model.exceptions;

import com.spotfinderbackend.shared.domain.model.exceptions.BusinessRuleException;

public class NoActiveEmergencyException extends BusinessRuleException {

    public NoActiveEmergencyException() {
        super("No active emergency to evacuate");
    }
}