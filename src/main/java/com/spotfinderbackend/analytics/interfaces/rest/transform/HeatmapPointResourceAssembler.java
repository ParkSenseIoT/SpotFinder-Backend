package com.spotfinderbackend.analytics.interfaces.rest.transform;

import com.spotfinderbackend.analytics.domain.model.readmodels.HeatmapPoint;
import com.spotfinderbackend.analytics.interfaces.rest.resources.HeatmapPointResource;

public class HeatmapPointResourceAssembler {

    public static HeatmapPointResource toResource(HeatmapPoint point) {
        return new HeatmapPointResource(
                point.slotCode(),
                point.usageCount()
        );
    }
}