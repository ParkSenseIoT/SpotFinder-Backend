package com.spotfinderbackend.emergency.interfaces.rest.resources;

import java.time.LocalDateTime;

public record EmergencyAlertResource(
        Long id,
        String description,
        String severity,
        String status,
        LocalDateTime createdAt
) {}