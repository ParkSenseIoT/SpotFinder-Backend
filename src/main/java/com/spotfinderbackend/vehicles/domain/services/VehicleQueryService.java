package com.spotfinderbackend.vehicles.domain.services;

import com.spotfinderbackend.vehicles.domain.model.aggregates.Vehicle;
import com.spotfinderbackend.vehicles.domain.model.queries.GetVehicleByPlateQuery;
import com.spotfinderbackend.vehicles.domain.model.queries.GetVehiclesByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface VehicleQueryService {

    List<Vehicle> handle(GetVehiclesByUserIdQuery query);
    Optional<Vehicle> handle(GetVehicleByPlateQuery query);
}