package com.spotfinderbackend.vehicles.interfaces.rest.transform;

import com.spotfinderbackend.vehicles.domain.model.commands.CreateVehicleCommand;
import com.spotfinderbackend.vehicles.interfaces.rest.resources.CreateVehicleResource;

public class CreateVehicleCommandFromResourceAssembler {

    public static CreateVehicleCommand toCommand(Long userId, CreateVehicleResource resource) {
        return new CreateVehicleCommand(
                userId,
                resource.plate(),
                resource.brand(),
                resource.model(),
                resource.color()
        );
    }
}