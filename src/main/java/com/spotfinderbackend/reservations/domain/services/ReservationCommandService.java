package com.spotfinderbackend.reservations.domain.services;

import com.spotfinderbackend.reservations.domain.model.aggregates.Reservation;
import com.spotfinderbackend.reservations.domain.model.commands.CancelReservationCommand;
import com.spotfinderbackend.reservations.domain.model.commands.ConfirmReservationCommand;
import com.spotfinderbackend.reservations.domain.model.commands.CreateReservationCommand;
import com.spotfinderbackend.reservations.domain.model.commands.ExpireGracePeriodCommand;

import java.util.Optional;

public interface ReservationCommandService {
    Optional<Reservation> handle(CreateReservationCommand command);
    Optional<Reservation> handle(ConfirmReservationCommand command);
    Optional<Reservation> handle(CancelReservationCommand command);

    /** Sweeps every PENDING reservation past its grace period and marks it EXPIRED. Returns count. */
    int handle(ExpireGracePeriodCommand command);
}
