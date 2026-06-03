package com.spotfinderbackend.reservations.domain.model.queries;

/**
 * Returns the reservations for a user that are still active
 * (status PENDING or CONFIRMED and not past their {@code reservedUntil}).
 */
public record GetActiveReservationsByUserQuery(Long userId) { }
