package com.spotfinderbackend.analytics.domain.model.queries;

import java.time.LocalDateTime;

public record GetHeatmapQuery(
        LocalDateTime start,
        LocalDateTime end
) {}