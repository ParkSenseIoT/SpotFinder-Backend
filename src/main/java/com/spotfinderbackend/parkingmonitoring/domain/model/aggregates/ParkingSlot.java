package com.spotfinderbackend.parkingmonitoring.domain.model.aggregates;

import com.spotfinderbackend.parkingmonitoring.domain.model.exceptions.InvalidParkingSlotStatusException;
import com.spotfinderbackend.parkingmonitoring.domain.model.valueobjects.ParkingSlotStatus;
import com.spotfinderbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class ParkingSlot extends AuditableAbstractAggregateRoot<ParkingSlot> {

    private String code; // Ej: A1, B12, etc.

    @Enumerated(EnumType.STRING)
    private ParkingSlotStatus status;

    protected ParkingSlot() {}

    public ParkingSlot(String code) {
        this.code = code;
        this.status = ParkingSlotStatus.AVAILABLE; // default
    }

    // DOMAIN LOGIC

    public void updateStatus(ParkingSlotStatus newStatus) {
        if (newStatus == null) {
            throw new InvalidParkingSlotStatusException();
        }

        this.status = newStatus;
    }

    public void markAsAvailable() {
        this.status = ParkingSlotStatus.AVAILABLE;
    }

    public void markAsOccupied() {
        this.status = ParkingSlotStatus.OCCUPIED;
    }

    public void markAsOutOfService() {
        this.status = ParkingSlotStatus.OUT_OF_SERVICE;
    }
}