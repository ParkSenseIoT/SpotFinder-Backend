package com.spotfinderbackend.analytics.interfaces.rest.transform;

import com.spotfinderbackend.analytics.domain.model.readmodels.OccupancyMetrics;
import com.spotfinderbackend.analytics.interfaces.rest.resources.OccupancyMetricsResource;

public class OccupancyMetricsResourceAssembler {

    public static OccupancyMetricsResource toResource(OccupancyMetrics metrics) {
        return new OccupancyMetricsResource(
                metrics.totalSlots(),
                metrics.occupiedSlots(),
                metrics.occupancyRate()
        );
    }
}