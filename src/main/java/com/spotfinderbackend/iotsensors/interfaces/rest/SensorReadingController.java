package com.spotfinderbackend.iotsensors.interfaces.rest;

import com.spotfinderbackend.iotsensors.domain.model.queries.GetLatestSensorReadingQuery;
import com.spotfinderbackend.iotsensors.domain.model.queries.GetSensorReadingsBySensorIdQuery;
import com.spotfinderbackend.iotsensors.domain.model.queries.GetSensorReadingsQuery;
import com.spotfinderbackend.iotsensors.domain.services.SensorReadingCommandService;
import com.spotfinderbackend.iotsensors.domain.services.SensorReadingQueryService;
import com.spotfinderbackend.iotsensors.interfaces.rest.resources.CreateSensorReadingResource;
import com.spotfinderbackend.iotsensors.interfaces.rest.resources.SensorReadingResource;
import com.spotfinderbackend.iotsensors.interfaces.rest.transform.CreateSensorReadingCommandFromResourceAssembler;
import com.spotfinderbackend.iotsensors.interfaces.rest.transform.SensorReadingResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sensor-readings")
public class SensorReadingController {

    private final SensorReadingCommandService commandService;
    private final SensorReadingQueryService queryService;

    public SensorReadingController(
            SensorReadingCommandService commandService,
            SensorReadingQueryService queryService
    ) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    // 🧪 TS38 → CREATE SENSOR READING (SIMULADO IoT)
    @PostMapping
    public ResponseEntity<SensorReadingResource> createReading(
            @RequestBody CreateSensorReadingResource resource
    ) {

        var command = CreateSensorReadingCommandFromResourceAssembler.toCommand(resource);

        Long readingId = commandService.handle(command);

        var readings = queryService.handle(new GetSensorReadingsQuery());

        var reading = readings.stream()
                .filter(r -> r.getId().equals(readingId))
                .findFirst()
                .orElseThrow();

        var response = SensorReadingResourceFromEntityAssembler.toResource(reading);

        return ResponseEntity.status(201).body(response);
    }

    // 📊 GET ALL READINGS
    @GetMapping
    public ResponseEntity<List<SensorReadingResource>> getAll() {

        var readings = queryService.handle(new GetSensorReadingsQuery());

        var response = readings.stream()
                .map(SensorReadingResourceFromEntityAssembler::toResource)
                .toList();

        return ResponseEntity.ok(response);
    }

    // 📡 GET BY SENSOR ID
    @GetMapping("/sensor/{sensorId}")
    public ResponseEntity<List<SensorReadingResource>> getBySensorId(
            @PathVariable Long sensorId
    ) {

        var readings = queryService.handle(new GetSensorReadingsBySensorIdQuery(sensorId));

        var response = readings.stream()
                .map(SensorReadingResourceFromEntityAssembler::toResource)
                .toList();

        return ResponseEntity.ok(response);
    }

    // ⚡ GET LATEST (clave para IoT real)
    @GetMapping("/sensor/{sensorId}/latest")
    public ResponseEntity<SensorReadingResource> getLatest(
            @PathVariable Long sensorId
    ) {

        var reading = queryService.handle(new GetLatestSensorReadingQuery(sensorId));

        var response = SensorReadingResourceFromEntityAssembler.toResource(reading);

        return ResponseEntity.ok(response);
    }
}