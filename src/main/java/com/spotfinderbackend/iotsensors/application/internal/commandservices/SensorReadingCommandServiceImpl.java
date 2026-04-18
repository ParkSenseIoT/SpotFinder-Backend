package com.spotfinderbackend.iotsensors.application.internal.commandservices;

import com.spotfinderbackend.iotsensors.domain.model.aggregates.SensorReading;
import com.spotfinderbackend.iotsensors.domain.model.commands.CreateSensorReadingCommand;
import com.spotfinderbackend.iotsensors.domain.model.commands.DeleteSensorReadingCommand;
import com.spotfinderbackend.iotsensors.domain.model.exceptions.SensorReadingNotFoundException;
import com.spotfinderbackend.iotsensors.domain.model.valueobjects.SensorType;
import com.spotfinderbackend.iotsensors.domain.services.SensorReadingCommandService;
import com.spotfinderbackend.iotsensors.infrastructure.persistence.jpa.repositories.SensorReadingRepository;
import org.springframework.stereotype.Service;

@Service
public class SensorReadingCommandServiceImpl implements SensorReadingCommandService {

    private final SensorReadingRepository repository;

    public SensorReadingCommandServiceImpl(SensorReadingRepository repository) {
        this.repository = repository;
    }

    @Override
    public Long handle(CreateSensorReadingCommand command) {

        var reading = new SensorReading(
                command.sensorId(),
                SensorType.valueOf(command.type()),
                command.value(),
                command.unit()
        );

        repository.save(reading);

        return reading.getId();
    }

    @Override
    public void handle(DeleteSensorReadingCommand command) {

        var reading = repository.findById(command.id())
                .orElseThrow(() ->
                        new SensorReadingNotFoundException(command.id())
                );

        repository.delete(reading);
    }
}