package com.spotfinderbackend.analytics.domain.model.readmodels;

public record OccupancyMetrics(
        int totalSlots,
        int occupiedSlots,
        double occupancyRate
) {}