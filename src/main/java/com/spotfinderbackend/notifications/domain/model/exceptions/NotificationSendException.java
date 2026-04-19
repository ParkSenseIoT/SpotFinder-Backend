package com.spotfinderbackend.notifications.domain.model.exceptions;

import com.spotfinderbackend.shared.domain.model.exceptions.BusinessRuleException;

public class NotificationSendException extends BusinessRuleException {

    public NotificationSendException(String message) {
        super("Notification failed: " + message);
    }
}