package com.spotfinderbackend.parkingsessions.interfaces.rest.transform;

import com.spotfinderbackend.parkingsessions.domain.model.commands.CreateParkingSessionCommand;
import com.spotfinderbackend.parkingsessions.interfaces.rest.resources.CreateParkingSessionResource;

public class CreateParkingSessionCommandFromResourceAssembler {

    public static CreateParkingSessionCommand toCommand(CreateParkingSessionResource resource) {
        return new CreateParkingSessionCommand(
                resource.vehicleId(),
                resource.parkingSlotId()
        );
    }
}