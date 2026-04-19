package com.spotfinderbackend.parkingsessions.interfaces.rest.resources;

public record CreateParkingSessionResource(
        Long vehicleId,
        Long parkingSlotId
) {}