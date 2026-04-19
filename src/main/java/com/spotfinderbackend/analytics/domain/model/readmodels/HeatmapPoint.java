package com.spotfinderbackend.analytics.domain.model.readmodels;

public record HeatmapPoint(
        String slotCode,
        int usageCount
) {}