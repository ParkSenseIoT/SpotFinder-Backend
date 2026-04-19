package com.spotfinderbackend.analytics.domain.model.queries;

import java.time.LocalDateTime;

public record GetRevenueMetricsQuery(
        LocalDateTime start,
        LocalDateTime end
) {}