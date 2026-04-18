package com.spotfinderbackend.iotsensors.domain.model.commands;

import com.spotfinderbackend.shared.domain.model.exceptions.BadRequestException;

public record CreateSensorReadingCommand(
        Long sensorId,
        String type,
        Double value,
        String unit
) {
    public CreateSensorReadingCommand {
        if (sensorId == null || sensorId <= 0)
            throw new BadRequestException("SensorId is required");

        if (type == null || type.isBlank())
            throw new BadRequestException("Sensor type is required");

        if (value == null)
            throw new BadRequestException("Sensor value is required");

        if (unit == null || unit.isBlank())
            throw new BadRequestException("Unit is required");
    }
}