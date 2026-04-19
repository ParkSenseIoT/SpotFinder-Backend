package com.spotfinderbackend.emergency.interfaces.rest.resources;

import com.spotfinderbackend.emergency.domain.model.valueobjects.EmergencySeverity;

public record CreateEmergencyAlertResource(
        String description,
        EmergencySeverity severity
) {}