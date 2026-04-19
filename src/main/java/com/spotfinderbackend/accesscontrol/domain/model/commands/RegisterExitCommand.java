package com.spotfinderbackend.accesscontrol.domain.model.commands;

import com.spotfinderbackend.shared.domain.model.exceptions.BadRequestException;

public record RegisterExitCommand(
        String plate
) {
    public RegisterExitCommand {
        if (plate == null || plate.isBlank())
            throw new BadRequestException("Plate is required");
    }
}