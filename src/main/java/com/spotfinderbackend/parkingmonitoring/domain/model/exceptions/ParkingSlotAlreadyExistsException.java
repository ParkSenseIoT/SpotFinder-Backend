package com.spotfinderbackend.parkingmonitoring.domain.model.exceptions;

import com.spotfinderbackend.shared.domain.model.exceptions.BusinessRuleException;

public class ParkingSlotAlreadyExistsException extends BusinessRuleException {
    public ParkingSlotAlreadyExistsException(String code) {
        super("Parking slot with code " + code + " already exists");
    }
}