package com.spotfinderbackend.reservations.application.internal.queryservices;

import com.spotfinderbackend.reservations.domain.model.aggregates.Reservation;
import com.spotfinderbackend.reservations.domain.model.queries.GetActiveReservationsByUserQuery;
import com.spotfinderbackend.reservations.domain.model.queries.GetReservationByIdQuery;
import com.spotfinderbackend.reservations.domain.model.queries.GetReservationHistoryByUserQuery;
import com.spotfinderbackend.reservations.domain.model.queries.GetReservationsBySlotQuery;
import com.spotfinderbackend.reservations.domain.model.valueobjects.ReservationStatus;
import com.spotfinderbackend.reservations.domain.services.ReservationQueryService;
import com.spotfinderbackend.reservations.infrastructure.persistence.jpa.repositories.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ReservationQueryServiceImpl implements ReservationQueryService {

    private final ReservationRepository reservationRepository;

    public ReservationQueryServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Optional<Reservation> handle(GetReservationByIdQuery query) {
        return reservationRepository.findById(query.reservationId());
    }

    @Override
    public List<Reservation> handle(GetActiveReservationsByUserQuery query) {
        return reservationRepository.findByUserId_ValueAndStatusInOrderByReservedFromAsc(
                query.userId(),
                List.of(ReservationStatus.PENDING, ReservationStatus.CONFIRMED));
    }

    @Override
    public List<Reservation> handle(GetReservationsBySlotQuery query) {
        return reservationRepository.findBySlotId_ValueOrderByCreatedAtDesc(query.slotId());
    }

    @Override
    public List<Reservation> handle(GetReservationHistoryByUserQuery query) {
        return reservationRepository.findByUserId_ValueOrderByCreatedAtDesc(query.userId());
    }
}
