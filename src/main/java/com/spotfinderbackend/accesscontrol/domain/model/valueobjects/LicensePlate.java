package com.spotfinderbackend.accesscontrol.domain.model.valueobjects;

import com.spotfinderbackend.shared.domain.model.exceptions.BadRequestException;

public record LicensePlate(String value) {

    public LicensePlate {
        if (value == null || value.isBlank())
            throw new BadRequestException("Plate is required");
    }
}