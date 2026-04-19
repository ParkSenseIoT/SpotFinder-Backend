package com.spotfinderbackend.notifications.interfaces.rest.resources;

import java.time.LocalDateTime;

public record NotificationResource(
        Long id,
        Long userId,
        String title,
        String message,
        String type,
        String status,
        LocalDateTime sentAt
) {}