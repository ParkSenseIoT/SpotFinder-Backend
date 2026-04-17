package com.spotfinderbackend.notifications.domain.model.exceptions;

import com.spotfinderbackend.shared.domain.model.exceptions.NotFoundException;

public class NotificationNotFoundException extends NotFoundException {

    public NotificationNotFoundException(Long id) {
        super("Notification with id " + id + " not found");
    }
}