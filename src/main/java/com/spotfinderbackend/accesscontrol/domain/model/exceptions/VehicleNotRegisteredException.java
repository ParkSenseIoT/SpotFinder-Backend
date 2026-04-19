package com.spotfinderbackend.accesscontrol.domain.model.exceptions;

import com.spotfinderbackend.shared.domain.model.exceptions.NotFoundException;

public class VehicleNotRegisteredException extends NotFoundException {

    public VehicleNotRegisteredException(String plate) {
        super("Vehicle with plate " + plate + " not registered");
    }
}