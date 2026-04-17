package com.spotfinderbackend.parkingmonitoring.domain.model.valueobjects;

import com.spotfinderbackend.shared.domain.model.exceptions.BadRequestException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class ParkingSlotCode {

    @Column(name = "code", nullable = false, unique = true)
    private String value;

    protected ParkingSlotCode() {}

    public ParkingSlotCode(String value) {
        if (value == null || value.isBlank()) {
            throw new BadRequestException("Parking slot code is required");
        }
        this.value = value;
    }

    public String value() {
        return value;
    }
}