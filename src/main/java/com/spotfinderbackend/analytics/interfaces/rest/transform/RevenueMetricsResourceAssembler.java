package com.spotfinderbackend.analytics.interfaces.rest.transform;

import com.spotfinderbackend.analytics.domain.model.readmodels.RevenueMetrics;
import com.spotfinderbackend.analytics.interfaces.rest.resources.RevenueMetricsResource;

public class RevenueMetricsResourceAssembler {

    public static RevenueMetricsResource toResource(RevenueMetrics metrics) {
        return new RevenueMetricsResource(
                metrics.totalRevenue(),
                metrics.totalTransactions()
        );
    }
}