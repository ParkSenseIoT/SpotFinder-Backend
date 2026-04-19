package com.spotfinderbackend.parkingmonitoring.interfaces.rest.resources;

import com.spotfinderbackend.parkingmonitoring.domain.model.valueobjects.ParkingSlotStatus;

public record UpdateParkingSlotStatusResource(
        ParkingSlotStatus status
) {}