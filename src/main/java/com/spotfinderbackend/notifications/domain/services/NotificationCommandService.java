package com.spotfinderbackend.notifications.domain.services;

import com.spotfinderbackend.notifications.domain.model.commands.CreateNotificationCommand;

public interface NotificationCommandService {

    Long handle(CreateNotificationCommand command);
}