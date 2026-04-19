package com.spotfinderbackend.notifications.interfaces.rest.transform;

import com.spotfinderbackend.notifications.domain.model.aggregates.Notification;
import com.spotfinderbackend.notifications.interfaces.rest.resources.NotificationResource;

public class NotificationResourceFromEntityAssembler {

    public static NotificationResource toResource(Notification notification) {
        return new NotificationResource(
                notification.getId(),
                notification.getUserId(),
                notification.getTitle(),
                notification.getMessage(),
                notification.getType().name(),
                notification.getStatus().name(),
                notification.getSentAt()
        );
    }
}