package com.spotfinderbackend.iotsensors.domain.infrastructure.persistence.jpa.repositories;

import com.spotfinderbackend.iotsensors.domain.model.aggregates.SensorReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorReadingRepository extends JpaRepository<SensorReading, Long> {

    List<SensorReading> findBySensorId(Long sensorId);
}