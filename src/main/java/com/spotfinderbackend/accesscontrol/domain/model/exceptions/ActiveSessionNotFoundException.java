package com.spotfinderbackend.accesscontrol.domain.model.exceptions;

import com.spotfinderbackend.shared.domain.model.exceptions.NotFoundException;

public class ActiveSessionNotFoundException extends NotFoundException {

    public ActiveSessionNotFoundException(Long vehicleId) {
        super("No active session found for vehicle " + vehicleId);
    }
}