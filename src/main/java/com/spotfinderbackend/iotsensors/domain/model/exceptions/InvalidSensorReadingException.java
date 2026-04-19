package com.spotfinderbackend.iotsensors.domain.model.exceptions;

import com.spotfinderbackend.shared.domain.model.exceptions.BadRequestException;

public class InvalidSensorReadingException extends BadRequestException {
    public InvalidSensorReadingException(String message) {
        super(message);
    }
}