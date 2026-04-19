package com.spotfinderbackend.emergency.domain.model.commands;

import com.spotfinderbackend.emergency.domain.model.valueobjects.EmergencySeverity;
import com.spotfinderbackend.shared.domain.model.exceptions.BadRequestException;

public record CreateEmergencyAlertCommand(
        String description,
        EmergencySeverity severity
) {
    public CreateEmergencyAlertCommand {
        if (description == null || description.isBlank())
            throw new BadRequestException("Description is required");

        if (severity == null)
            throw new BadRequestException("Severity is required");
    }
}