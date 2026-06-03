package com.spotfinderbackend.reservations.interfaces.rest.resources;

import java.time.LocalDateTime;

/**
 * Inbound DTO for POST /api/v1/reservations.
 *
 * @param userId             driver requesting the reservation
 * @param slotId             slot to reserve
 * @param reservedFrom       moment the reservation becomes active; if null defaults to now
 * @param gracePeriodMinutes optional override of the default 15-minute grace period
 */
public record CreateReservationResource(
        Long userId,
        Long slotId,
        LocalDateTime reservedFrom,
        Integer gracePeriodMinutes
) { }
