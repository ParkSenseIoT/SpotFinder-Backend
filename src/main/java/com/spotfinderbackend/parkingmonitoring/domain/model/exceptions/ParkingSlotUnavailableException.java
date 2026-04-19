package com.spotfinderbackend.parkingmonitoring.domain.model.exceptions;

import com.spotfinderbackend.shared.domain.model.exceptions.BusinessRuleException;

public class ParkingSlotUnavailableException extends BusinessRuleException {
    public ParkingSlotUnavailableException(Long id) {
        super("Parking slot " + id + " is not available");
    }
}