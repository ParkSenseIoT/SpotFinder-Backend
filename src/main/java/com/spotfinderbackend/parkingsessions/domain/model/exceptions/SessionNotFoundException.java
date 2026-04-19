package com.spotfinderbackend.parkingsessions.domain.model.exceptions;

import com.spotfinderbackend.shared.domain.model.exceptions.NotFoundException;

public class SessionNotFoundException extends NotFoundException {
    public SessionNotFoundException(Long id) {
        super("Parking session " + id + " not found");
    }
}