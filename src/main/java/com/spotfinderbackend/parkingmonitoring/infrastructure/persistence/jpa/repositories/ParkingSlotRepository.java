package com.spotfinderbackend.parkingmonitoring.infrastructure.persistence.jpa.repositories;

import com.spotfinderbackend.parkingmonitoring.domain.model.aggregates.ParkingSlot;
import com.spotfinderbackend.parkingmonitoring.domain.model.valueobjects.ParkingSlotCode;
import com.spotfinderbackend.parkingmonitoring.domain.model.valueobjects.ParkingSlotStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {

    // TS32 → disponibles
    List<ParkingSlot> findByStatus(ParkingSlotStatus status);

    // para validaciones (ej: evitar duplicados de código)
    Optional<ParkingSlot> findByCode(ParkingSlotCode code);

    boolean existsByCode(ParkingSlotCode code);
}