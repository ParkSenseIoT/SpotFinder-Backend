package com.spotfinderbackend.emergency.interfaces.rest.transform;

import com.spotfinderbackend.emergency.domain.model.aggregates.EmergencyAlert;
import com.spotfinderbackend.emergency.interfaces.rest.resources.EmergencyAlertResource;

public class EmergencyAlertResourceFromEntityAssembler {

    public static EmergencyAlertResource toResource(EmergencyAlert alert) {
        return new EmergencyAlertResource(
                alert.getId(),
                alert.getDescription(),
                alert.getSeverity().name(),
                alert.getStatus().name(),
                alert.getCreatedAt()
        );
    }
}