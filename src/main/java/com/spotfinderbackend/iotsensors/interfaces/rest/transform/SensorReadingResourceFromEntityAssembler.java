package com.spotfinderbackend.iotsensors.interfaces.rest.transform;

import com.spotfinderbackend.iotsensors.domain.model.aggregates.SensorReading;
import com.spotfinderbackend.iotsensors.interfaces.rest.resources.SensorReadingResource;

public class SensorReadingResourceFromEntityAssembler {

    public static SensorReadingResource toResource(SensorReading reading) {
        return new SensorReadingResource(
                reading.getId(),
                reading.getSensorId(),
                reading.getType().name(),
                reading.getStatus().name(),
                reading.getValue(),
                reading.getUnit()
        );
    }
}