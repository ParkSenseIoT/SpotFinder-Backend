package com.spotfinderbackend.iotsensors.domain.model.exceptions;

import com.spotfinderbackend.shared.domain.model.exceptions.NotFoundException;

public class SensorNotFoundException extends NotFoundException {
    public SensorNotFoundException(Long id) {
        super("Sensor with id " + id + " not found");
    }
}