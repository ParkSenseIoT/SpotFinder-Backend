package com.spotfinderbackend.emergency.domain.model.commands;

import com.spotfinderbackend.shared.domain.model.exceptions.BadRequestException;

public record ResolveEmergencyCommand(
        Long alertId
) {
    public ResolveEmergencyCommand {
        if (alertId == null || alertId <= 0)
            throw new BadRequestException("AlertId is required");
    }
}