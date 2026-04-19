package com.spotfinderbackend.vehicles.domain.model.commands;

import com.spotfinderbackend.shared.domain.model.exceptions.BadRequestException;

public record DeleteVehicleCommand(
        Long userId,
        Long vehicleId
) {
    public DeleteVehicleCommand {
        if (userId == null || userId <= 0)
            throw new BadRequestException("UserId is required");

        if (vehicleId == null || vehicleId <= 0)
            throw new BadRequestException("VehicleId is required");
    }
}