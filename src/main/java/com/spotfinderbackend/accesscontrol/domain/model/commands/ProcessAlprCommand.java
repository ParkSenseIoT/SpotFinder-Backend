package com.spotfinderbackend.accesscontrol.domain.model.commands;

import com.spotfinderbackend.shared.domain.model.exceptions.BadRequestException;

public record ProcessAlprCommand(
        String imageBase64
) {
    public ProcessAlprCommand {
        if (imageBase64 == null || imageBase64.isBlank())
            throw new BadRequestException("Image is required");
    }
}