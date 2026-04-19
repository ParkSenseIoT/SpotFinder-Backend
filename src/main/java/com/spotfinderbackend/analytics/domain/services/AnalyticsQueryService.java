package com.spotfinderbackend.analytics.domain.services;

import com.spotfinderbackend.analytics.domain.model.queries.*;
import com.spotfinderbackend.analytics.domain.model.readmodels.*;

import java.util.List;

public interface AnalyticsQueryService {

    OccupancyMetrics handle(GetOccupancyMetricsQuery query);

    RevenueMetrics handle(GetRevenueMetricsQuery query);

    List<HeatmapPoint> handle(GetHeatmapQuery query);
}