package com.spotfinderbackend.parkingmonitoring.domain.model.exceptions;

import com.spotfinderbackend.shared.domain.model.exceptions.NotFoundException;

public class ParkingSlotNotFoundException extends NotFoundException {
    public ParkingSlotNotFoundException(Long id) {
        super("Parking slot " + id + " not found");
    }
}