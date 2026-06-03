package com.spotfinderbackend.reservations.domain.model.commands;

import com.spotfinderbackend.shared.domain.model.exceptions.BadRequestException;

import java.time.LocalDateTime;

/**
 * Command issued by a driver to reserve a specific parking slot starting at the given
 * timestamp. The grace period defines how long the slot is held after {@code reservedFrom}
 * before the reservation auto-expires if the driver does not enter the facility.
 *
 * <p>If {@code gracePeriodMinutes} is null or non-positive the domain default applies (15 min).</p>
 */
public record CreateReservationCommand(
        Long userId,
        Long slotId,
        LocalDateTime reservedFrom,
        Integer gracePeriodMinutes
) {

    public static final int DEFAULT_GRACE_PERIOD_MINUTES = 15;

    public CreateReservationCommand {
        if (userId == null || userId <= 0)
            throw new BadRequestException("userId must be > 0");
        if (slotId == null || slotId <= 0)
            throw new BadRequestException("slotId must be > 0");
        if (reservedFrom == null)
            throw new BadRequestException("reservedFrom must not be null");
    }

    public int effectiveGracePeriodMinutes() {
        return (gracePeriodMinutes == null || gracePeriodMinutes <= 0)
                ? DEFAULT_GRACE_PERIOD_MINUTES
                : gracePeriodMinutes;
    }
}
