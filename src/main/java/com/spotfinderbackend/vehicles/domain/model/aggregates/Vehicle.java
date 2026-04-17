package com.spotfinderbackend.vehicles.domain.model.aggregates;

import com.spotfinderbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.spotfinderbackend.vehicles.domain.model.exceptions.DuplicatePlateException;
import com.spotfinderbackend.vehicles.domain.model.valueobjects.Plate;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Vehicle extends AuditableAbstractAggregateRoot<Vehicle> {

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "plate", unique = true))
    private Plate plate;

    private Long userId;

    private String brand;
    private String model;
    private String color;

    protected Vehicle() {}

    public Vehicle(Long userId, Plate plate, String brand, String model, String color) {
        if (userId == null)
            throw new IllegalArgumentException("UserId cannot be null");

        this.userId = userId;
        this.plate = plate;
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    // DOMAIN BEHAVIOR

    public void updateInfo(String brand, String model, String color) {
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    public void changePlate(Plate newPlate) {
        if (this.plate.equals(newPlate))
            throw new DuplicatePlateException(newPlate.getValue());

        this.plate = newPlate;
    }
}