package com.spotfinderbackend.emergency.domain.model.exceptions;

import com.spotfinderbackend.shared.domain.model.exceptions.NotFoundException;

public class EmergencyNotFoundException extends NotFoundException {

    public EmergencyNotFoundException(Long id) {
        super("Emergency alert not found: " + id);
    }
}