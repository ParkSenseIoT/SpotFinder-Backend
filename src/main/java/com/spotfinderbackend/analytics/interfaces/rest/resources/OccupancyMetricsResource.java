package com.spotfinderbackend.analytics.interfaces.rest.resources;

public record OccupancyMetricsResource(
        int totalSlots,
        int occupiedSlots,
        double occupancyRate
) {}