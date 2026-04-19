package com.spotfinderbackend.parkingsessions.domain.model.commands;

import com.spotfinderbackend.shared.domain.model.exceptions.BadRequestException;

public record EndParkingSessionCommand(
        Long sessionId
) {
    public EndParkingSessionCommand {
        if (sessionId == null || sessionId <= 0)
            throw new BadRequestException("SessionId must be a valid positive number");
    }
}