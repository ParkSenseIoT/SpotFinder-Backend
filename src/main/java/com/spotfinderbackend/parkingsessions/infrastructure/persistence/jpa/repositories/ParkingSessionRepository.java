package com.spotfinderbackend.parkingsessions.infrastructure.persistence.jpa.repositories;

import com.spotfinderbackend.parkingsessions.domain.model.aggregates.ParkingSession;
import com.spotfinderbackend.parkingsessions.domain.model.valueobjects.SessionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParkingSessionRepository extends JpaRepository<ParkingSession, Long> {

    // TS40 → Obtener sesión activa
    Optional<ParkingSession> findByVehicleIdAndStatus(Long vehicleId, SessionStatus status);

    // TS39 → Validar si ya existe sesión activa
    boolean existsByVehicleIdAndStatus(Long vehicleId, SessionStatus status);

    // TS43 → Historial
    List<ParkingSession> findByVehicleId(Long vehicleId);
}