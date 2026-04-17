package com.spotfinderbackend.parkingmonitoring.domain.model.valueobjects;

import com.spotfinderbackend.shared.domain.model.exceptions.BadRequestException;

public record ParkingSlotCode(String value) {

    public ParkingSlotCode {
        if (value == null || value.isBlank()) {
            throw new BadRequestException("Parking slot code is required");
        }
    }
}