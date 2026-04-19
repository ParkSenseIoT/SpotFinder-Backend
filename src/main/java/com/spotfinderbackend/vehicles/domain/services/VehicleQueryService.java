package com.spotfinderbackend.vehicles.domain.services;

import com.spotfinderbackend.vehicles.domain.model.aggregates.Vehicle;
import com.spotfinderbackend.vehicles.domain.model.queries.GetVehiclesByUserIdQuery;

import java.util.List;

public interface VehicleQueryService {

    List<Vehicle> handle(GetVehiclesByUserIdQuery query);
}