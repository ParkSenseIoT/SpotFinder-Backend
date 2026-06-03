package com.spotfinderbackend.reservations.interfaces.rest.resources;

/** Inbound DTO for PATCH /api/v1/reservations/{id}/cancel. */
public record CancelReservationResource(String reason) { }
