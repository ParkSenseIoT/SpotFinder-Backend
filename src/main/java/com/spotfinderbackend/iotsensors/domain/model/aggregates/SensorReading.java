package com.spotfinderbackend.iotsensors.domain.model.aggregates;

import com.spotfinderbackend.iotsensors.domain.model.valueobjects.SensorStatus;
import com.spotfinderbackend.iotsensors.domain.model.valueobjects.SensorType;
import com.spotfinderbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class SensorReading extends AuditableAbstractAggregateRoot<SensorReading> {

    private Long sensorId;

    @Enumerated(EnumType.STRING)
    private SensorType type;

    @Enumerated(EnumType.STRING)
    private SensorStatus status;

    private Double value;

    private String unit;

    private LocalDateTime recordedAt;

    protected SensorReading() {}

    public SensorReading(Long sensorId,
                         SensorType type,
                         SensorStatus status,
                         Double value,
                         String unit) {

        this.sensorId = sensorId;
        this.type = type;
        this.status = status;
        this.value = value;
        this.unit = unit;
        this.recordedAt = LocalDateTime.now();
    }

    // DOMAIN LOGIC

    public boolean isCritical() {
        return value != null && value > 0; // simplificado (LUEGO SE MEJORA)
    }

    public void markAsError() {
        this.status = SensorStatus.ERROR;
    }
}