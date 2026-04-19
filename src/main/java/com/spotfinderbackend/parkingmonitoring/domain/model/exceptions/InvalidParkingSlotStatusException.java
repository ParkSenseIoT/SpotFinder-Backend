package com.spotfinderbackend.parkingmonitoring.domain.model.exceptions;

import com.spotfinderbackend.shared.domain.model.exceptions.BadRequestException;

public class InvalidParkingSlotStatusException extends BadRequestException {
    public InvalidParkingSlotStatusException() {
        super("Invalid parking slot status");
    }
}