package com.spotfinderbackend.parkingmonitoring.interfaces.rest;

import com.spotfinderbackend.parkingmonitoring.domain.model.queries.*;
import com.spotfinderbackend.parkingmonitoring.domain.services.ParkingSlotCommandService;
import com.spotfinderbackend.parkingmonitoring.domain.services.ParkingSlotQueryService;
import com.spotfinderbackend.parkingmonitoring.interfaces.rest.resources.CreateParkingSlotResource;
import com.spotfinderbackend.parkingmonitoring.interfaces.rest.resources.ParkingSlotResource;
import com.spotfinderbackend.parkingmonitoring.interfaces.rest.resources.UpdateParkingSlotStatusResource;
import com.spotfinderbackend.parkingmonitoring.interfaces.rest.transform.CreateParkingSlotCommandFromResourceAssembler;
import com.spotfinderbackend.parkingmonitoring.interfaces.rest.transform.ParkingSlotResourceFromEntityAssembler;
import com.spotfinderbackend.parkingmonitoring.interfaces.rest.transform.UpdateParkingSlotStatusCommandFromResourceAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parking-slots")
public class ParkingSlotController {

    private final ParkingSlotCommandService commandService;
    private final ParkingSlotQueryService queryService;

    public ParkingSlotController(
            ParkingSlotCommandService commandService,
            ParkingSlotQueryService queryService
    ) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    // CREATE SLOT
    @PostMapping
    public ResponseEntity<ParkingSlotResource> createSlot(
            @RequestBody CreateParkingSlotResource resource
    ) {

        var command = CreateParkingSlotCommandFromResourceAssembler.toCommand(resource);

        Long slotId = commandService.handle(command);

        var slots = queryService.handle(new GetAllParkingSlotsQuery());

        var slot = slots.stream()
                .filter(s -> s.getId().equals(slotId))
                .findFirst()
                .orElseThrow();

        var response = ParkingSlotResourceFromEntityAssembler.toResource(slot);

        return ResponseEntity.status(201).body(response);
    }

    // TS31 → LIST ALL
    @GetMapping
    public ResponseEntity<List<ParkingSlotResource>> getAll() {

        var slots = queryService.handle(new GetAllParkingSlotsQuery());

        var response = slots.stream()
                .map(ParkingSlotResourceFromEntityAssembler::toResource)
                .toList();

        return ResponseEntity.ok(response);
    }

    // TS32 → AVAILABLE
    @GetMapping("/available")
    public ResponseEntity<List<ParkingSlotResource>> getAvailable() {

        var slots = queryService.handle(new GetAvailableParkingSlotsQuery());

        var response = slots.stream()
                .map(ParkingSlotResourceFromEntityAssembler::toResource)
                .toList();

        return ResponseEntity.ok(response);
    }

    // TS34 → RECOMMENDATIONS
    @GetMapping("/recommendations")
    public ResponseEntity<List<ParkingSlotResource>> getRecommendations() {

        var slots = queryService.handle(new GetRecommendedParkingSlotsQuery());

        var response = slots.stream()
                .map(ParkingSlotResourceFromEntityAssembler::toResource)
                .toList();

        return ResponseEntity.ok(response);
    }

    // TS33 → UPDATE STATUS
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(
            @PathVariable Long id,
            @RequestBody UpdateParkingSlotStatusResource resource
    ) {

        var command = UpdateParkingSlotStatusCommandFromResourceAssembler.toCommand(id, resource);

        commandService.handle(command);

        return ResponseEntity.ok().build();
    }
}