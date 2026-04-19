package com.spotfinderbackend.iotsensors.interfaces.rest.transform;

import com.spotfinderbackend.iotsensors.domain.model.commands.CreateSensorReadingCommand;
import com.spotfinderbackend.iotsensors.interfaces.rest.resources.CreateSensorReadingResource;

public class CreateSensorReadingCommandFromResourceAssembler {

    public static CreateSensorReadingCommand toCommand(CreateSensorReadingResource resource) {
        return new CreateSensorReadingCommand(
                resource.sensorId(),
                resource.type(),
                resource.value(),
                resource.unit()
        );
    }
}