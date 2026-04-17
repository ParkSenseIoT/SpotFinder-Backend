package com.spotfinderbackend.parkingmonitoring.interfaces.rest.resources;

public record ParkingSlotResource(
        Long id,
        String code,
        String status
) {}