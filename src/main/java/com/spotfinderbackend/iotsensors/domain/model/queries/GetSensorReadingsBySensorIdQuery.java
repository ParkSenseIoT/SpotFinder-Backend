package com.spotfinderbackend.iotsensors.domain.model.queries;

import com.spotfinderbackend.shared.domain.model.exceptions.BadRequestException;

public record GetSensorReadingsBySensorIdQuery(Long sensorId) {

    public GetSensorReadingsBySensorIdQuery {
        if (sensorId == null || sensorId <= 0)
            throw new BadRequestException("SensorId is required");
    }
}