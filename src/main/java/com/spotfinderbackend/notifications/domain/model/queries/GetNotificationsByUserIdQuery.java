package com.spotfinderbackend.notifications.domain.model.queries;

public record GetNotificationsByUserIdQuery(
        Long userId
) {
    public GetNotificationsByUserIdQuery {
        if (userId == null || userId <= 0)
            throw new IllegalArgumentException("UserId is required");
    }
}