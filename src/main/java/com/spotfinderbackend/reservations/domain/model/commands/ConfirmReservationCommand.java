package com.spotfinderbackend.reservations.domain.model.commands;

import com.spotfinderbackend.shared.domain.model.exceptions.BadRequestException;

/**
 * Command issued by Access Control BC (after a successful ALPR entry) to mark
 * the reservation as honored / confirmed.
 */
public record ConfirmReservationCommand(Long reservationId) {

    public ConfirmReservationCommand {
        if (reservationId == null || reservationId <= 0)
            throw new BadRequestException("reservationId must be > 0");
    }
}
