package com.spotfinderbackend.vehicles.domain.services;

import com.spotfinderbackend.vehicles.domain.model.commands.CreateVehicleCommand;
import com.spotfinderbackend.vehicles.domain.model.commands.DeleteVehicleCommand;

public interface VehicleCommandService {

    Long handle(CreateVehicleCommand command);

    void handle(DeleteVehicleCommand command);
}