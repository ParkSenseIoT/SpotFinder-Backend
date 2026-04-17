package com.spotfinderbackend.parkingmonitoring.domain.model.commands;

import com.spotfinderbackend.parkingmonitoring.domain.model.valueobjects.ParkingSlotStatus;
import com.spotfinderbackend.shared.domain.model.exceptions.BadRequestException;

public record UpdateParkingSlotStatusCommand(
        Long slotId,
        ParkingSlotStatus status
) {
    public UpdateParkingSlotStatusCommand {
        if (slotId == null || slotId <= 0) {
            throw new BadRequestException("ParkingSlotId is required");
        }

        if (status == null) {
            throw new BadRequestException("Parking slot status is required");
        }
    }
}