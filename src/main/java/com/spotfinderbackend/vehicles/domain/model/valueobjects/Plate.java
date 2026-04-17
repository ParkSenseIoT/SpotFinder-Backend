package com.spotfinderbackend.vehicles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.Objects;
import java.util.regex.Pattern;

@Getter
@Embeddable
public class Plate {

    private static final Pattern PLATE_PATTERN = Pattern.compile("^[A-Z0-9-]{6,10}$");

    private String value;

    protected Plate() {}

    public Plate(String value) {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException("Plate cannot be null or empty");

        value = value.toUpperCase();

        if (!PLATE_PATTERN.matcher(value).matches())
            throw new IllegalArgumentException("Invalid plate format");

        this.value = value;
    }

    // igualdad por valor
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plate)) return false;
        Plate plate = (Plate) o;
        return value.equals(plate.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}