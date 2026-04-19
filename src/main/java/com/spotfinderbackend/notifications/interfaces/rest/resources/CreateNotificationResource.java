package com.spotfinderbackend.notifications.interfaces.rest.resources;

public record CreateNotificationResource(
        Long userId,
        String title,
        String type,
        String message
) {}