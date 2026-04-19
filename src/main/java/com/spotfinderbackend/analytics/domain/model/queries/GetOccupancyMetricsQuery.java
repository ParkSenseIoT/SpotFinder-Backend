package com.spotfinderbackend.analytics.domain.model.queries;

import java.time.LocalDateTime;

public record GetOccupancyMetricsQuery(
        LocalDateTime start,
        LocalDateTime end
) {}