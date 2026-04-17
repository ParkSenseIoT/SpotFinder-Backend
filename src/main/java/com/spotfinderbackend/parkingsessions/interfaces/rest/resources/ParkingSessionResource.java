package com.spotfinderbackend.parkingsessions.interfaces.rest.resources;

import java.time.LocalDateTime;

public record ParkingSessionResource(
        Long id,
        Long vehicleId,
        Long parkingSlotId,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String status
) {}