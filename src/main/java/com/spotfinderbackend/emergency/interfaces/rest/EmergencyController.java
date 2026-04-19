package com.spotfinderbackend.emergency.interfaces.rest;

import com.spotfinderbackend.emergency.domain.model.commands.ActivateEvacuationCommand;
import com.spotfinderbackend.emergency.domain.model.commands.ResolveEmergencyCommand;
import com.spotfinderbackend.emergency.domain.model.queries.GetEmergencyStatusQuery;
import com.spotfinderbackend.emergency.domain.services.EmergencyCommandService;
import com.spotfinderbackend.emergency.domain.services.EmergencyQueryService;
import com.spotfinderbackend.emergency.interfaces.rest.resources.CreateEmergencyAlertResource;
import com.spotfinderbackend.emergency.interfaces.rest.resources.EmergencyAlertResource;
import com.spotfinderbackend.emergency.interfaces.rest.transform.CreateEmergencyAlertCommandFromResourceAssembler;
import com.spotfinderbackend.emergency.interfaces.rest.transform.EmergencyAlertResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class EmergencyController {

    private final EmergencyCommandService commandService;
    private final EmergencyQueryService queryService;

    public EmergencyController(
            EmergencyCommandService commandService,
            EmergencyQueryService queryService
    ) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    // TS54 → CREATE ALERT
    @PostMapping("/emergency/alerts")
    public ResponseEntity<EmergencyAlertResource> createAlert(
            @RequestBody CreateEmergencyAlertResource resource
    ) {

        var command = CreateEmergencyAlertCommandFromResourceAssembler.toCommand(resource);

        Long alertId = commandService.handle(command);

        var alert = queryService.handle(new GetEmergencyStatusQuery());

        var response = EmergencyAlertResourceFromEntityAssembler.toResource(alert);

        return ResponseEntity.status(201).body(response);
    }

    // TS55 → GET STATUS
    @GetMapping("/emergency/status")
    public ResponseEntity<EmergencyAlertResource> getStatus() {

        var alert = queryService.handle(new GetEmergencyStatusQuery());

        var response = EmergencyAlertResourceFromEntityAssembler.toResource(alert);

        return ResponseEntity.ok(response);
    }

    // TS56 → ACTIVATE EVACUATION
    @PostMapping("/emergency/evacuate")
    public ResponseEntity<Void> evacuate() {

        commandService.handle(new ActivateEvacuationCommand());

        return ResponseEntity.ok().build();
    }

    // TS57 → RESOLVE ALERT
    @PatchMapping("/emergencies/{id}/resolve")
    public ResponseEntity<Void> resolve(
            @PathVariable Long id
    ) {

        commandService.handle(new ResolveEmergencyCommand(id));

        return ResponseEntity.ok().build();
    }
}