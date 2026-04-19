package com.spotfinderbackend.iotsensors.interfaces.rest.resources;

public record SensorReadingResource(
        Long id,
        Long sensorId,
        String type,
        String status,
        Double value,
        String unit
) {}