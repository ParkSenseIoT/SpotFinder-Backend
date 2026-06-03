package com.spotfinderbackend.reservations.interfaces.rest.resources;

import java.time.LocalDateTime;

/** Outbound DTO exposed by the Reservations REST API. */
public record ReservationResource(
        Long id,
        Long userId,
        Long slotId,
        String status,
        LocalDateTime reservedFrom,
        LocalDateTime reservedUntil,
        int gracePeriodMinutes,
        LocalDateTime confirmedAt,
        LocalDateTime cancelledAt,
        LocalDateTime expiredAt,
        String cancellationReason,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) { }
