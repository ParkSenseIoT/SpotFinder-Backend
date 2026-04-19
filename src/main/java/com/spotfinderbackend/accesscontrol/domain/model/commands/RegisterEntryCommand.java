package com.spotfinderbackend.accesscontrol.domain.model.commands;

import com.spotfinderbackend.shared.domain.model.exceptions.BadRequestException;

public record RegisterEntryCommand(
        String plate
) {
    public RegisterEntryCommand {
        if (plate == null || plate.isBlank())
            throw new BadRequestException("Plate is required");
    }
}