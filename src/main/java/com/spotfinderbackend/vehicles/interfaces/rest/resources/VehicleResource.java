package com.spotfinderbackend.vehicles.interfaces.rest.resources;

public record VehicleResource(
        Long id,
        String plate,
        String brand,
        String model,
        String color
) {}