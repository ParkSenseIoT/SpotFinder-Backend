package com.spotfinderbackend.reservations.infrastructure.persistence.jpa.repositories;

import com.spotfinderbackend.reservations.domain.model.aggregates.Reservation;
import com.spotfinderbackend.reservations.domain.model.valueobjects.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByUserId_ValueOrderByCreatedAtDesc(Long userId);

    List<Reservation> findByUserId_ValueAndStatusInOrderByReservedFromAsc(Long userId,
                                                                         List<ReservationStatus> statuses);

    List<Reservation> findBySlotId_ValueOrderByCreatedAtDesc(Long slotId);

    List<Reservation> findByStatusAndReservedUntilBefore(ReservationStatus status, LocalDateTime cutoff);

    boolean existsBySlotId_ValueAndStatus(Long slotId, ReservationStatus status);
}
