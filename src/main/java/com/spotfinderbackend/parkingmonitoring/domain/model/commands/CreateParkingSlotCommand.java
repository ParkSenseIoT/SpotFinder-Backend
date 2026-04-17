package com.spotfinderbackend.parkingmonitoring.domain.model.commands;

import com.spotfinderbackend.shared.domain.model.exceptions.BadRequestException;

public record CreateParkingSlotCommand(
        String code
) {
    public CreateParkingSlotCommand {
        if (code == null || code.isBlank()) {
            throw new BadRequestException("Parking slot code is required");
        }
    }
}