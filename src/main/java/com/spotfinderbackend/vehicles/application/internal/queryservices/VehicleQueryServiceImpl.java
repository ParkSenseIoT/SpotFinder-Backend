package com.spotfinderbackend.vehicles.application.internal.queryservices;

import com.spotfinderbackend.vehicles.domain.model.aggregates.Vehicle;
import com.spotfinderbackend.vehicles.domain.model.queries.GetVehiclesByUserIdQuery;
import com.spotfinderbackend.vehicles.domain.services.VehicleQueryService;
import com.spotfinderbackend.vehicles.infrastructure.persistence.jpa.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleQueryServiceImpl implements VehicleQueryService {

    private final VehicleRepository vehicleRepository;

    public VehicleQueryServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<Vehicle> handle(GetVehiclesByUserIdQuery query) {
        return vehicleRepository.findByUserId(query.userId());
    }
}