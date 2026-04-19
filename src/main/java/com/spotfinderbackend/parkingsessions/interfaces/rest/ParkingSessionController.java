package com.spotfinderbackend.parkingsessions.interfaces.rest;

import com.spotfinderbackend.parkingsessions.domain.model.commands.EndParkingSessionCommand;
import com.spotfinderbackend.parkingsessions.domain.model.queries.*;
import com.spotfinderbackend.parkingsessions.domain.services.ParkingSessionCommandService;
import com.spotfinderbackend.parkingsessions.domain.services.ParkingSessionQueryService;
import com.spotfinderbackend.parkingsessions.interfaces.rest.resources.CreateParkingSessionResource;
import com.spotfinderbackend.parkingsessions.interfaces.rest.resources.ParkingSessionResource;
import com.spotfinderbackend.parkingsessions.interfaces.rest.transform.CreateParkingSessionCommandFromResourceAssembler;
import com.spotfinderbackend.parkingsessions.interfaces.rest.transform.ParkingSessionResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parking-sessions")
public class ParkingSessionController {

    private final ParkingSessionCommandService commandService;
    private final ParkingSessionQueryService queryService;

    public ParkingSessionController(
            ParkingSessionCommandService commandService,
            ParkingSessionQueryService queryService
    ) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    // TS39 → CREATE SESSION
    @PostMapping
    public ResponseEntity<ParkingSessionResource> createSession(
            @RequestBody CreateParkingSessionResource resource
    ) {

        var command = CreateParkingSessionCommandFromResourceAssembler.toCommand(resource);

        Long sessionId = commandService.handle(command);

        var session = queryService.handle(new GetParkingSessionByIdQuery(sessionId));

        var response = ParkingSessionResourceFromEntityAssembler.toResource(session);

        return ResponseEntity.status(201).body(response); // 201 Created
    }

    // TS40 → GET ACTIVE SESSION
    @GetMapping("/active")
    public ResponseEntity<ParkingSessionResource> getActiveSession(
            @RequestParam Long vehicleId
    ) {

        var query = new GetActiveSessionQuery(vehicleId);

        var session = queryService.handle(query);

        var response = ParkingSessionResourceFromEntityAssembler.toResource(session);

        return ResponseEntity.ok(response); // 200 OK
    }

    // TS41 → GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ParkingSessionResource> getSessionById(
            @PathVariable Long id
    ) {

        var query = new GetParkingSessionByIdQuery(id);

        var session = queryService.handle(query);

        var response = ParkingSessionResourceFromEntityAssembler.toResource(session);

        return ResponseEntity.ok(response);
    }

    // TS42 → END SESSION
    @PatchMapping("/{id}/end")
    public ResponseEntity<Void> endSession(
            @PathVariable Long id
    ) {

        var command = new EndParkingSessionCommand(id);

        commandService.handle(command);

        return ResponseEntity.ok().build(); // 200 OK
    }

    // TS43 → HISTORY
    @GetMapping("/history")
    public ResponseEntity<List<ParkingSessionResource>> getHistory(
            @RequestParam Long vehicleId
    ) {

        var query = new GetParkingSessionHistoryQuery(vehicleId);

        var sessions = queryService.handle(query);

        var response = sessions.stream()
                .map(ParkingSessionResourceFromEntityAssembler::toResource)
                .toList();

        return ResponseEntity.ok(response); // lista vacía OK
    }
}