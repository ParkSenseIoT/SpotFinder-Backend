package com.spotfinderbackend.parkingsessions.domain.model.queries;

import com.spotfinderbackend.shared.domain.model.exceptions.BadRequestException;

public record GetParkingSessionByIdQuery(Long sessionId) {

    public GetParkingSessionByIdQuery {
        if (sessionId == null || sessionId <= 0)
            throw new BadRequestException("SessionId must be a valid positive number");
    }
}