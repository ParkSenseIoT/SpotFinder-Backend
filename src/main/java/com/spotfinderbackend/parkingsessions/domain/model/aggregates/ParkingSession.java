package com.spotfinderbackend.parkingsessions.domain.model.aggregates;

import com.spotfinderbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.spotfinderbackend.parkingsessions.domain.model.exceptions.*;
import com.spotfinderbackend.parkingsessions.domain.model.valueobjects.SessionStatus;
import com.spotfinderbackend.shared.domain.model.exceptions.BadRequestException;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class ParkingSession extends AuditableAbstractAggregateRoot<ParkingSession> {

    private Long vehicleId;
    private Long parkingSlotId;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private SessionStatus status;

    protected ParkingSession() {}

    public ParkingSession(Long vehicleId, Long parkingSlotId) {

        if (vehicleId == null)
            throw new BadRequestException("VehicleId is required");

        if (parkingSlotId == null)
            throw new BadRequestException("ParkingSlotId is required");

        this.vehicleId = vehicleId;
        this.parkingSlotId = parkingSlotId;
        this.startTime = LocalDateTime.now();
        this.status = SessionStatus.ACTIVE;
    }

    // DOMAIN BEHAVIOR

    public void markAsPaid() {
        if (this.status != SessionStatus.ACTIVE)
            throw new InvalidSessionStateException("Only active sessions can be paid");

        this.status = SessionStatus.PAID;
    }

    public void endSession() {
        if (this.status != SessionStatus.PAID)
            throw new PaymentRequiredException();

        this.status = SessionStatus.CLOSED;
        this.endTime = LocalDateTime.now();
    }

    public boolean isActive() {
        return this.status == SessionStatus.ACTIVE;
    }
}