package com.spotfinderbackend.reservations.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * Value Object that references a parking slot from the Parking Monitoring BC.
 * Stored flattened as {@code slot_id} BIGINT NOT NULL in the {@code reservations} table.
 */
@Embeddable
public record ReservationSlotId(@Column(name = "slot_id", nullable = false) Long value) {

    public ReservationSlotId() { this(0L); }

    public ReservationSlotId {
        if (value == null || value <= 0)
            throw new IllegalArgumentException("ReservationSlotId must be > 0");
    }
}
