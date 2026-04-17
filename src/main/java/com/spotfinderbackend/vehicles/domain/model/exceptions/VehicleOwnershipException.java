package com.spotfinderbackend.vehicles.domain.model.exceptions;

public class VehicleOwnershipException extends RuntimeException {
    public VehicleOwnershipException(String message) {
        super(message);
    }
}
