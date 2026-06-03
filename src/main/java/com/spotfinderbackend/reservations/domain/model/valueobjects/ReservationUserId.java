package com.spotfinderbackend.reservations.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * Value Object that references the user (driver) that owns the reservation.
 * Stored flattened as {@code user_id} BIGINT NOT NULL in the {@code reservations} table.
 */
@Embeddable
public record ReservationUserId(@Column(name = "user_id", nullable = false) Long value) {

    public ReservationUserId() { this(0L); }

    public ReservationUserId {
        if (value == null || value <= 0)
            throw new IllegalArgumentException("ReservationUserId must be > 0");
    }
}
