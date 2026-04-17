package com.spotfinderbackend.vehicles.domain.model.exceptions;

import com.spotfinderbackend.shared.domain.model.exceptions.BusinessRuleException;

public class VehicleOwnershipException extends BusinessRuleException {
    public VehicleOwnershipException() {
        super("Vehicle does not belong to the user");
    }
}