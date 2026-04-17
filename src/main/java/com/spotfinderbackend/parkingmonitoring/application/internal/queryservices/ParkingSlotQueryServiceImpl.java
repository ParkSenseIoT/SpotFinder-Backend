package com.spotfinderbackend.parkingmonitoring.application.internal.queryservices;

import com.spotfinderbackend.parkingmonitoring.domain.model.aggregates.ParkingSlot;
import com.spotfinderbackend.parkingmonitoring.domain.model.queries.GetAllParkingSlotsQuery;
import com.spotfinderbackend.parkingmonitoring.domain.model.queries.GetAvailableParkingSlotsQuery;
import com.spotfinderbackend.parkingmonitoring.domain.model.queries.GetRecommendedParkingSlotsQuery;
import com.spotfinderbackend.parkingmonitoring.domain.model.valueobjects.ParkingSlotStatus;
import com.spotfinderbackend.parkingmonitoring.domain.services.ParkingSlotQueryService;
import com.spotfinderbackend.parkingmonitoring.infrastructure.persistence.jpa.repositories.ParkingSlotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSlotQueryServiceImpl implements ParkingSlotQueryService {

    private final ParkingSlotRepository repository;

    public ParkingSlotQueryServiceImpl(ParkingSlotRepository repository) {
        this.repository = repository;
    }

    // TS31 → Listar todos los espacios
    @Override
    public List<ParkingSlot> handle(GetAllParkingSlotsQuery query) {
        return repository.findAll();
    }

    // TS32 → Solo disponibles
    @Override
    public List<ParkingSlot> handle(GetAvailableParkingSlotsQuery query) {
        return repository.findByStatus(ParkingSlotStatus.AVAILABLE);
    }

    // TS34 → Recomendaciones MEJORAR PARA QUE DE UNA MEJOR RECOMENDACIÓN
    @Override
    public List<ParkingSlot> handle(GetRecommendedParkingSlotsQuery query) {

        // simplemente retorna disponibles

        return repository.findByStatus(ParkingSlotStatus.AVAILABLE);
    }
}