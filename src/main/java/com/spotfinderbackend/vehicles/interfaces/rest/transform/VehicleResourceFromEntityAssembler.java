package com.spotfinderbackend.vehicles.interfaces.rest.transform;

import com.spotfinderbackend.vehicles.domain.model.aggregates.Vehicle;
import com.spotfinderbackend.vehicles.interfaces.rest.resources.VehicleResource;

public class VehicleResourceFromEntityAssembler {

    public static VehicleResource toResource(Vehicle vehicle) {
        return new VehicleResource(
                vehicle.getId(),
                vehicle.getPlate().getValue(),
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getColor()
        );
    }
}