package com.spotfinderbackend.notifications.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class NotificationDeliveryLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String provider; // FCM

    private String externalId;

    private LocalDateTime timestamp;

    protected NotificationDeliveryLog() {}

    public NotificationDeliveryLog(String provider, String externalId) {
        this.provider = provider;
        this.externalId = externalId;
        this.timestamp = LocalDateTime.now();
    }
}