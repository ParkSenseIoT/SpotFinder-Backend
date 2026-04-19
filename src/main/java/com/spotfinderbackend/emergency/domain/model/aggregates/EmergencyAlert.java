package com.spotfinderbackend.emergency.domain.model.aggregates;

import com.spotfinderbackend.emergency.domain.model.valueobjects.EmergencySeverity;
import com.spotfinderbackend.emergency.domain.model.valueobjects.EmergencyStatus;
import com.spotfinderbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class EmergencyAlert extends AuditableAbstractAggregateRoot<EmergencyAlert> {

    private String description;

    @Enumerated(EnumType.STRING)
    private EmergencySeverity severity;

    @Enumerated(EnumType.STRING)
    private EmergencyStatus status;

    private LocalDateTime createdAt;

    protected EmergencyAlert() {}

    public EmergencyAlert(String description, EmergencySeverity severity) {
        this.description = description;
        this.severity = severity;
        this.status = EmergencyStatus.ACTIVE;
        this.createdAt = LocalDateTime.now();
    }

    // DOMAIN LOGIC

    public void resolve() {
        this.status = EmergencyStatus.RESOLVED;
    }

    public boolean isActive() {
        return this.status == EmergencyStatus.ACTIVE;
    }
}