package com.spotfinderbackend.notifications.domain.model.commands;

import com.spotfinderbackend.notifications.domain.model.valueobjects.NotificationType;
import com.spotfinderbackend.shared.domain.model.exceptions.BadRequestException;

public record CreateNotificationCommand(
        Long userId,
        String title,
        NotificationType type,
        String message
) {
    public CreateNotificationCommand {
        if (userId == null || userId <= 0)
            throw new BadRequestException("UserId is required");

        if (title == null || title.isBlank())
            throw new BadRequestException("Title is required");

        if (type == null)
            throw new BadRequestException("Notification type is required");

        if (message == null || message.isBlank())
            throw new BadRequestException("Message is required");
    }
}