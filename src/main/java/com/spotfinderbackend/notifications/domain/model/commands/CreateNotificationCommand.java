package com.spotfinderbackend.notifications.domain.model.commands;

import com.spotfinderbackend.shared.domain.model.exceptions.BadRequestException;

public record CreateNotificationCommand(
        Long userId,
        String type,
        String message
) {
    public CreateNotificationCommand {
        if (userId == null || userId <= 0)
            throw new BadRequestException("UserId is required");

        if (type == null || type.isBlank())
            throw new BadRequestException("Notification type is required");

        if (message == null || message.isBlank())
            throw new BadRequestException("Message is required");
    }
}