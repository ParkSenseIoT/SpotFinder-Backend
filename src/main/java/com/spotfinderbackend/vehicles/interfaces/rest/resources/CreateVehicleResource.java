package com.spotfinderbackend.vehicles.interfaces.rest.resources;

public record CreateVehicleResource(
        String plate,
        String brand,
        String model,
        String color
) {}