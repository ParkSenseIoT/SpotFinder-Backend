package com.spotfinderbackend.reservations.interfaces.rest.controllers;

import com.spotfinderbackend.reservations.domain.model.commands.CancelReservationCommand;
import com.spotfinderbackend.reservations.domain.model.commands.ExpireGracePeriodCommand;
import com.spotfinderbackend.reservations.domain.model.queries.GetActiveReservationsByUserQuery;
import com.spotfinderbackend.reservations.domain.model.queries.GetReservationByIdQuery;
import com.spotfinderbackend.reservations.domain.model.queries.GetReservationHistoryByUserQuery;
import com.spotfinderbackend.reservations.domain.model.queries.GetReservationsBySlotQuery;
import com.spotfinderbackend.reservations.domain.services.ReservationCommandService;
import com.spotfinderbackend.reservations.domain.services.ReservationQueryService;
import com.spotfinderbackend.reservations.interfaces.rest.resources.CancelReservationResource;
import com.spotfinderbackend.reservations.interfaces.rest.resources.CreateReservationResource;
import com.spotfinderbackend.reservations.interfaces.rest.resources.ReservationResource;
import com.spotfinderbackend.reservations.interfaces.rest.transform.CreateReservationCommandFromResourceAssembler;
import com.spotfinderbackend.reservations.interfaces.rest.transform.ReservationResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/reservations", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Reservations", description = "Pro/Premium parking-slot reservations with grace period")
public class ReservationsController {

    private final ReservationCommandService commandService;
    private final ReservationQueryService queryService;

    public ReservationsController(ReservationCommandService commandService,
                                  ReservationQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @Operation(summary = "Create a reservation for a parking slot")
    @PostMapping
    public ResponseEntity<ReservationResource> create(@RequestBody CreateReservationResource resource) {
        var command = CreateReservationCommandFromResourceAssembler.toCommandFromResource(resource);
        return commandService.handle(command)
                .map(r -> new ResponseEntity<>(ReservationResourceFromEntityAssembler.toResourceFromEntity(r), HttpStatus.CREATED))
                .orElse(ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Get a reservation by id")
    @GetMapping("/{id}")
    public ResponseEntity<ReservationResource> getById(@PathVariable Long id) {
        return queryService.handle(new GetReservationByIdQuery(id))
                .map(r -> ResponseEntity.ok(ReservationResourceFromEntityAssembler.toResourceFromEntity(r)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "List active reservations for a user (PENDING + CONFIRMED, not past grace)")
    @GetMapping("/active")
    public ResponseEntity<List<ReservationResource>> active(@RequestParam Long userId) {
        var list = queryService.handle(new GetActiveReservationsByUserQuery(userId));
        return ResponseEntity.ok(ReservationResourceFromEntityAssembler.toResourcesFromEntities(list));
    }

    @Operation(summary = "List full reservation history for a user")
    @GetMapping("/history")
    public ResponseEntity<List<ReservationResource>> history(@RequestParam Long userId) {
        var list = queryService.handle(new GetReservationHistoryByUserQuery(userId));
        return ResponseEntity.ok(ReservationResourceFromEntityAssembler.toResourcesFromEntities(list));
    }

    @Operation(summary = "List reservations associated with a slot (admin / Parking Monitoring)")
    @GetMapping("/slot/{slotId}")
    public ResponseEntity<List<ReservationResource>> bySlot(@PathVariable Long slotId) {
        var list = queryService.handle(new GetReservationsBySlotQuery(slotId));
        return ResponseEntity.ok(ReservationResourceFromEntityAssembler.toResourcesFromEntities(list));
    }

    @Operation(summary = "Cancel a PENDING reservation")
    @PatchMapping("/{id}/cancel")
    public ResponseEntity<ReservationResource> cancel(@PathVariable Long id,
                                                      @RequestBody(required = false) CancelReservationResource body) {
        String reason = body == null ? null : body.reason();
        return commandService.handle(new CancelReservationCommand(id, reason))
                .map(r -> ResponseEntity.ok(ReservationResourceFromEntityAssembler.toResourceFromEntity(r)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Force the grace-period sweep (admin / cron). Returns count expired.")
    @PostMapping("/sweep-expirations")
    public ResponseEntity<Integer> sweepExpirations() {
        return ResponseEntity.ok(commandService.handle(new ExpireGracePeriodCommand()));
    }
}
