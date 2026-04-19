package com.spotfinderbackend.vehicles.infrastructure.persistence.jpa.repositories;

import com.spotfinderbackend.vehicles.domain.model.aggregates.Vehicle;
import com.spotfinderbackend.vehicles.domain.model.valueobjects.Plate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByUserId(Long userId);

    Optional<Vehicle> findByPlate(Plate plate);

    boolean existsByPlate(Plate plate);
}