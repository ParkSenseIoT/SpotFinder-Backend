package com.spotfinderbackend.vehicles.domain.model.commands;

import com.spotfinderbackend.shared.domain.model.exceptions.BadRequestException;

public record CreateVehicleCommand(
        Long userId,
        String plate,
        String brand,
        String model,
        String color
) {
    public CreateVehicleCommand {
        if (userId == null || userId <= 0)
            throw new BadRequestException("UserId is required");

        if (plate == null || plate.isBlank())
            throw new BadRequestException("Plate is required");
    }
}