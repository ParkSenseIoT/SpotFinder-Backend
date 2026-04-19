package com.spotfinderbackend.iotsensors.domain.model.exceptions;

import com.spotfinderbackend.shared.domain.model.exceptions.NotFoundException;

public class SensorReadingsNotFoundException extends NotFoundException {

    public SensorReadingsNotFoundException(Long sensorId) {
        super("No sensor readings found for sensor " + sensorId);
    }
}