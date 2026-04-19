package com.spotfinderbackend.iotsensors.interfaces.rest.resources;

public record CreateSensorReadingResource(
        Long sensorId,
        String type,
        Double value,
        String unit
) {}