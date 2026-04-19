package com.spotfinderbackend.emergency.infrastructure.persistence.jpa.repositories;

import com.spotfinderbackend.emergency.domain.model.aggregates.EmergencyAlert;
import com.spotfinderbackend.emergency.domain.model.valueobjects.EmergencyStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmergencyRepository extends JpaRepository<EmergencyAlert, Long> {

    Optional<EmergencyAlert> findByStatus(EmergencyStatus status);
}