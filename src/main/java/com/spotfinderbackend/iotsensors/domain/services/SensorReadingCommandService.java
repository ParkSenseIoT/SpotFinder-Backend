package com.spotfinderbackend.iotsensors.domain.services;

import com.spotfinderbackend.iotsensors.domain.model.commands.CreateSensorReadingCommand;
import com.spotfinderbackend.iotsensors.domain.model.commands.DeleteSensorReadingCommand;

public interface SensorReadingCommandService {

    Long handle(CreateSensorReadingCommand command);

    void handle(DeleteSensorReadingCommand command);
}