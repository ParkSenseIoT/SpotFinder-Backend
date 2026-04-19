package com.spotfinderbackend.iotsensors.domain.services;

import com.spotfinderbackend.iotsensors.domain.model.aggregates.SensorReading;
import com.spotfinderbackend.iotsensors.domain.model.queries.GetSensorReadingsBySensorIdQuery;
import com.spotfinderbackend.iotsensors.domain.model.queries.GetSensorReadingsQuery;
import com.spotfinderbackend.iotsensors.domain.model.queries.GetLatestSensorReadingQuery;

import java.util.List;

public interface SensorReadingQueryService {

    List<SensorReading> handle(GetSensorReadingsQuery query);

    List<SensorReading> handle(GetSensorReadingsBySensorIdQuery query);

    SensorReading handle(GetLatestSensorReadingQuery query);
}