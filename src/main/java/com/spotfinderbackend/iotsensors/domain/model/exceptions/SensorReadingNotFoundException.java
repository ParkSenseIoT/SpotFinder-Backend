package com.spotfinderbackend.iotsensors.domain.model.exceptions;

public class SensorReadingNotFoundException extends RuntimeException {
    public SensorReadingNotFoundException(Long id){
        super("Sensor reading with id " + id + " not found");
    }
}
