package com.spotfinderbackend.emergency.domain.services;

import com.spotfinderbackend.emergency.domain.model.aggregates.EmergencyAlert;
import com.spotfinderbackend.emergency.domain.model.queries.GetEmergencyStatusQuery;

public interface EmergencyQueryService {

    EmergencyAlert handle(GetEmergencyStatusQuery query);
}