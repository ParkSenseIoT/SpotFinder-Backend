package com.spotfinderbackend.parkingsessions.domain.model.commands;

import com.spotfinderbackend.shared.domain.model.exceptions.BadRequestException;

public record CreateParkingSessionCommand(
        Long vehicleId,
        Long parkingSlotId
) {
    public CreateParkingSessionCommand {
        if (vehicleId == null || vehicleId <= 0)
            throw new BadRequestException("VehicleId must be a valid positive number");

        if (parkingSlotId == null || parkingSlotId <= 0)
            throw new BadRequestException("ParkingSlotId must be a valid positive number");
    }
}