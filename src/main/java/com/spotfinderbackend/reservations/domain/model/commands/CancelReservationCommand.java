package com.spotfinderbackend.reservations.domain.model.commands;

import com.spotfinderbackend.shared.domain.model.exceptions.BadRequestException;

/**
 * Command issued by the driver (or by an admin) to cancel a reservation
 * before it expires or is confirmed.
 */
public record CancelReservationCommand(Long reservationId, String reason) {

    public CancelReservationCommand {
        if (reservationId == null || reservationId <= 0)
            throw new BadRequestException("reservationId must be > 0");
    }
}
