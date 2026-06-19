package com.spotfinderbackend.reservations.application.internal.commandservices;

import com.spotfinderbackend.reservations.application.internal.outboundservices.acl.ExternalIamService;
import com.spotfinderbackend.reservations.application.internal.outboundservices.acl.ExternalParkingMonitoringService;
import com.spotfinderbackend.reservations.domain.model.aggregates.Reservation;
import com.spotfinderbackend.reservations.domain.model.commands.CancelReservationCommand;
import com.spotfinderbackend.reservations.domain.model.commands.ConfirmReservationCommand;
import com.spotfinderbackend.reservations.domain.model.commands.CreateReservationCommand;
import com.spotfinderbackend.reservations.domain.model.commands.ExpireGracePeriodCommand;
import com.spotfinderbackend.reservations.domain.model.valueobjects.ReservationStatus;
import com.spotfinderbackend.reservations.domain.services.ReservationCommandService;
import com.spotfinderbackend.reservations.infrastructure.persistence.jpa.repositories.ReservationRepository;
import com.spotfinderbackend.shared.domain.model.exceptions.ConflictException;
import com.spotfinderbackend.shared.domain.model.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservationCommandServiceImpl implements ReservationCommandService {

    private final ReservationRepository reservationRepository;
    private final ExternalIamService externalIamService;
    private final ExternalParkingMonitoringService externalParkingMonitoringService;

    public ReservationCommandServiceImpl(ReservationRepository reservationRepository,
                                         ExternalIamService externalIamService,
                                         ExternalParkingMonitoringService externalParkingMonitoringService) {
        this.reservationRepository = reservationRepository;
        this.externalIamService = externalIamService;
        this.externalParkingMonitoringService = externalParkingMonitoringService;
    }

    @Override
    public Optional<Reservation> handle(CreateReservationCommand command) {
        if (!externalIamService.isActiveUser(command.userId()))
            throw new NotFoundException("User not found or inactive: " + command.userId());

        if (!externalParkingMonitoringService.slotExists(command.slotId()))
            throw new NotFoundException("Parking slot not found: " + command.slotId());

        if (reservationRepository.existsBySlotId_ValueAndStatus(command.slotId(), ReservationStatus.PENDING))
            throw new ConflictException("Slot already has an active reservation");

        var reservation = new Reservation(command);
        return Optional.of(reservationRepository.save(reservation));
    }

    @Override
    public Optional<Reservation> handle(ConfirmReservationCommand command) {
        var reservation = reservationRepository.findById(command.reservationId())
                .orElseThrow(() -> new NotFoundException("Reservation not found: " + command.reservationId()));
        reservation.confirm();
        return Optional.of(reservationRepository.save(reservation));
    }

    @Override
    public Optional<Reservation> handle(CancelReservationCommand command) {
        var reservation = reservationRepository.findById(command.reservationId())
                .orElseThrow(() -> new NotFoundException("Reservation not found: " + command.reservationId()));
        reservation.cancel(command.reason());
        return Optional.of(reservationRepository.save(reservation));
    }

    @Override
    public int handle(ExpireGracePeriodCommand command) {
        List<Reservation> expirable = reservationRepository
                .findByStatusAndReservedUntilBefore(ReservationStatus.PENDING, LocalDateTime.now());
        int count = 0;
        for (Reservation r : expirable) {
            r.expire();
            reservationRepository.save(r);
            count++;
        }
        return count;
    }
}
