package com.spotfinderbackend.vehicles.interfaces.rest;

import com.spotfinderbackend.vehicles.domain.model.commands.DeleteVehicleCommand;
import com.spotfinderbackend.vehicles.domain.model.queries.GetVehiclesByUserIdQuery;
import com.spotfinderbackend.vehicles.domain.services.VehicleCommandService;
import com.spotfinderbackend.vehicles.domain.services.VehicleQueryService;
import com.spotfinderbackend.vehicles.interfaces.rest.resources.CreateVehicleResource;
import com.spotfinderbackend.vehicles.interfaces.rest.resources.VehicleResource;
import com.spotfinderbackend.vehicles.interfaces.rest.transform.CreateVehicleCommandFromResourceAssembler;
import com.spotfinderbackend.vehicles.interfaces.rest.transform.VehicleResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class VehicleController {

    private final VehicleCommandService commandService;
    private final VehicleQueryService queryService;

    public VehicleController(
            VehicleCommandService commandService,
            VehicleQueryService queryService
    ) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    // TS28 → REGISTER VEHICLE
    @PostMapping("/users/{userId}/vehicles")
    public ResponseEntity<VehicleResource> createVehicle(
            @PathVariable Long userId,
            @RequestBody CreateVehicleResource resource
    ) {

        var command = CreateVehicleCommandFromResourceAssembler.toCommand(userId, resource);

        Long vehicleId = commandService.handle(command);

        var vehicles = queryService.handle(new GetVehiclesByUserIdQuery(userId));

        var vehicle = vehicles.stream()
                .filter(v -> v.getId().equals(vehicleId))
                .findFirst()
                .orElseThrow();

        var response = VehicleResourceFromEntityAssembler.toResource(vehicle);

        return ResponseEntity.status(201).body(response); // TS: 201 Created
    }

    // TS29 → LIST VEHICLES
    @GetMapping("/users/{userId}/vehicles")
    public ResponseEntity<List<VehicleResource>> getVehiclesByUser(
            @PathVariable Long userId
    ) {

        var query = new GetVehiclesByUserIdQuery(userId);

        var vehicles = queryService.handle(query);

        var response = vehicles.stream()
                .map(VehicleResourceFromEntityAssembler::toResource)
                .toList();

        return ResponseEntity.ok(response); // 200 OK (lista vacía si no hay)
    }

    // TS30 → DELETE VEHICLE
    @DeleteMapping("/users/{userId}/vehicles/{vehicleId}")
    public ResponseEntity<Void> deleteVehicle(
            @PathVariable Long userId,
            @PathVariable Long vehicleId
    ) {

        var command = new DeleteVehicleCommand(userId, vehicleId);

        commandService.handle(command);

        return ResponseEntity.noContent().build(); // 204 No Content
    }
}