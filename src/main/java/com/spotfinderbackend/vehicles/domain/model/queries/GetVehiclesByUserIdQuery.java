package com.spotfinderbackend.vehicles.domain.model.queries;

import com.spotfinderbackend.shared.domain.model.exceptions.BadRequestException;

public record GetVehiclesByUserIdQuery(Long userId) {

    public GetVehiclesByUserIdQuery {
        if (userId == null || userId <= 0)
            throw new BadRequestException("UserId is required");
    }
}