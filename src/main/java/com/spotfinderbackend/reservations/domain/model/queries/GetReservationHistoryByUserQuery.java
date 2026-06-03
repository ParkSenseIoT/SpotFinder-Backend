package com.spotfinderbackend.reservations.domain.model.queries;

/**
 * Full history of reservations for a user, ordered by creation date desc.
 * Includes EXPIRED and CANCELLED reservations.
 */
public record GetReservationHistoryByUserQuery(Long userId) { }
