package com.spotfinderbackend.reservations.domain.services;

import com.spotfinderbackend.reservations.domain.model.aggregates.Reservation;
import com.spotfinderbackend.reservations.domain.model.queries.GetActiveReservationsByUserQuery;
import com.spotfinderbackend.reservations.domain.model.queries.GetReservationByIdQuery;
import com.spotfinderbackend.reservations.domain.model.queries.GetReservationHistoryByUserQuery;
import com.spotfinderbackend.reservations.domain.model.queries.GetReservationsBySlotQuery;

import java.util.List;
import java.util.Optional;

public interface ReservationQueryService {
    Optional<Reservation> handle(GetReservationByIdQuery query);
    List<Reservation> handle(GetActiveReservationsByUserQuery query);
    List<Reservation> handle(GetReservationsBySlotQuery query);
    List<Reservation> handle(GetReservationHistoryByUserQuery query);
}
