package com.spotfinderbackend.parkingsessions.domain.model.exceptions;

import com.spotfinderbackend.shared.domain.model.exceptions.BusinessRuleException;

public class SessionAlreadyActiveException extends BusinessRuleException {
    public SessionAlreadyActiveException(Long vehicleId) {
        super("Vehicle " + vehicleId + " already has an active session");
    }
}