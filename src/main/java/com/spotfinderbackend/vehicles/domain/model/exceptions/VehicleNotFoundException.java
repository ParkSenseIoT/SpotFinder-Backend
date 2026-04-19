package com.spotfinderbackend.vehicles.domain.model.exceptions;

import com.spotfinderbackend.shared.domain.model.exceptions.NotFoundException;

public class VehicleNotFoundException extends NotFoundException {
    public VehicleNotFoundException(Long id) {
        super("Vehicle with id " + id + " not found");
    }
}