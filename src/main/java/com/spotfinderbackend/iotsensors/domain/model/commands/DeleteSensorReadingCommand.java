package com.spotfinderbackend.iotsensors.domain.model.commands;

import com.spotfinderbackend.shared.domain.model.exceptions.BadRequestException;

public record DeleteSensorReadingCommand(Long id) {

    public DeleteSensorReadingCommand {
        if (id == null || id <= 0)
            throw new BadRequestException("SensorReadingId is required");
    }
}