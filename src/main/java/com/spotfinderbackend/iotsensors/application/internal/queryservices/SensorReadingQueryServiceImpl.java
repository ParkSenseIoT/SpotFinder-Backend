package com.spotfinderbackend.iotsensors.application.internal.queryservices;

import com.spotfinderbackend.iotsensors.domain.model.aggregates.SensorReading;
import com.spotfinderbackend.iotsensors.domain.model.exceptions.SensorReadingNotFoundException;
import com.spotfinderbackend.iotsensors.domain.model.queries.GetLatestSensorReadingQuery;
import com.spotfinderbackend.iotsensors.domain.model.queries.GetSensorReadingsBySensorIdQuery;
import com.spotfinderbackend.iotsensors.domain.model.queries.GetSensorReadingsQuery;
import com.spotfinderbackend.iotsensors.domain.services.SensorReadingQueryService;
import com.spotfinderbackend.iotsensors.infrastructure.persistence.jpa.repositories.SensorReadingRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class SensorReadingQueryServiceImpl implements SensorReadingQueryService {

    private final SensorReadingRepository repository;

    public SensorReadingQueryServiceImpl(SensorReadingRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SensorReading> handle(GetSensorReadingsQuery query) {
        return repository.findAll();
    }

    @Override
    public List<SensorReading> handle(GetSensorReadingsBySensorIdQuery query) {
        return repository.findBySensorId(query.sensorId());
    }

    @Override
    public SensorReading handle(GetLatestSensorReadingQuery query) {

        return repository.findBySensorId(query.sensorId())
                .stream()
                .max(Comparator.comparing(SensorReading::getCreatedAt))
                .orElseThrow(() ->
                        new SensorReadingNotFoundException(query.sensorId())
                );
    }
}