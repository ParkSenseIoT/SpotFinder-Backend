package com.spotfinderbackend.vehicles.application.internal.queryservices;

import com.spotfinderbackend.vehicles.domain.model.aggregates.Vehicle;
import com.spotfinderbackend.vehicles.domain.model.queries.GetVehicleByPlateQuery;
import com.spotfinderbackend.vehicles.domain.model.queries.GetVehiclesByUserIdQuery;
import com.spotfinderbackend.vehicles.domain.model.valueobjects.Plate;
import com.spotfinderbackend.vehicles.domain.services.VehicleQueryService;
import com.spotfinderbackend.vehicles.infrastructure.persistence.jpa.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Vehicle> handle(GetVehicleByPlateQuery query) {
        var plate = new Plate(query.plate());
        return vehicleRepository.findByPlate(plate);
    }
}