package com.spotfinderbackend.analytics.interfaces.rest.resources;

public record HeatmapPointResource(
        String slotCode,
        int usageCount
) {}