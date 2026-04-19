package com.spotfinderbackend.accesscontrol.domain.model.exceptions;

import com.spotfinderbackend.shared.domain.model.exceptions.BusinessRuleException;

public class ActiveSessionAlreadyExistsException extends BusinessRuleException {

    public ActiveSessionAlreadyExistsException(Long vehicleId) {
        super("Vehicle " + vehicleId + " already has an active session");
    }
}