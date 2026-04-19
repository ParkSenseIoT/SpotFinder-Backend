package com.spotfinderbackend.parkingsessions.domain.model.queries;

import com.spotfinderbackend.shared.domain.model.exceptions.BadRequestException;

public record GetParkingSessionHistoryQuery(Long vehicleId) {

    public GetParkingSessionHistoryQuery {
        if (vehicleId == null || vehicleId <= 0)
            throw new BadRequestException("VehicleId must be a valid positive number");
    }
}