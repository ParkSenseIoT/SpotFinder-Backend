package com.spotfinderbackend.reservations.application.acl;

import com.spotfinderbackend.reservations.domain.model.aggregates.Reservation;
import com.spotfinderbackend.reservations.domain.model.valueobjects.ReservationStatus;
import com.spotfinderbackend.reservations.infrastructure.persistence.jpa.repositories.ReservationRepository;
import com.spotfinderbackend.reservations.interfaces.acl.ReservationContextFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationContextFacadeImpl implements ReservationContextFacade {

    private final ReservationRepository reservationRepository;

    public ReservationContextFacadeImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ActiveReservationView> findActiveReservationForUser(Long userId) {
        var active = reservationRepository.findByUserId_ValueAndStatusInOrderByReservedFromAsc(
                userId, List.of(ReservationStatus.PENDING, ReservationStatus.CONFIRMED));
        return active.stream()
                .filter(r -> r.getReservedUntil().isAfter(LocalDateTime.now()))
                .findFirst()
                .map(r -> new ActiveReservationView(r.getId(), r.getUserId().value(), r.getSlotId().value(),
                        r.getReservedFrom(), r.getReservedUntil()));
    }

    @Override
    @Transactional
    public Optional<Long> confirmIfActiveReservationFor(Long userId) {
        var pending = reservationRepository.findByUserId_ValueAndStatusInOrderByReservedFromAsc(
                userId, List.of(ReservationStatus.PENDING));
        Optional<Reservation> candidate = pending.stream()
                .filter(r -> r.getReservedUntil().isAfter(LocalDateTime.now()))
                .findFirst();
        candidate.ifPresent(Reservation::confirm);
        candidate.ifPresent(reservationRepository::save);
        return candidate.map(Reservation::getId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isSlotReserved(Long slotId) {
        return reservationRepository.existsBySlotId_ValueAndStatus(slotId, ReservationStatus.PENDING);
    }
}
