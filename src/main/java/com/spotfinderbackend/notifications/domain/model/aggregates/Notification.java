package com.spotfinderbackend.notifications.domain.model.aggregates;

import com.spotfinderbackend.notifications.domain.model.valueobjects.NotificationStatus;
import com.spotfinderbackend.notifications.domain.model.valueobjects.NotificationType;
import com.spotfinderbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Notification extends AuditableAbstractAggregateRoot<Notification> {

    private Long userId;

    private String title;

    private String message;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    private LocalDateTime sentAt;

    protected Notification() {}

    public Notification(Long userId, String title, String message, NotificationType type) {
        this.userId = userId;
        this.title = title;
        this.message = message;
        this.type = type;
        this.status = NotificationStatus.SENT;
        this.sentAt = LocalDateTime.now();
    }

    // DOMAIN LOGIC

    public void markAsFailed() {
        this.status = NotificationStatus.FAILED;
    }

    public void markAsSent() {
        this.status = NotificationStatus.SENT;
        this.sentAt = LocalDateTime.now();
    }
}