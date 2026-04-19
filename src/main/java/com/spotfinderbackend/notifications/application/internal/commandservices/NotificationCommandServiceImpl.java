package com.spotfinderbackend.notifications.application.internal.commandservices;

import com.spotfinderbackend.notifications.domain.model.aggregates.Notification;
import com.spotfinderbackend.notifications.domain.model.commands.CreateNotificationCommand;
import com.spotfinderbackend.notifications.domain.model.valueobjects.NotificationType;
import com.spotfinderbackend.notifications.domain.services.NotificationCommandService;
import com.spotfinderbackend.notifications.infrastructure.persistence.jpa.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationCommandServiceImpl implements NotificationCommandService {

    private final NotificationRepository repository;

    public NotificationCommandServiceImpl(NotificationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Long handle(CreateNotificationCommand command) {

        var notification = new Notification(
                command.userId(),
                command.title(),
                command.message(),
                command.type()
        );

        repository.save(notification);

        return notification.getId();
    }
}