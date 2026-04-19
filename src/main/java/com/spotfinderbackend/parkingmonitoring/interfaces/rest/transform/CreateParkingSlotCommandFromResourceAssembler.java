package com.spotfinderbackend.parkingmonitoring.interfaces.rest.transform;

import com.spotfinderbackend.parkingmonitoring.domain.model.commands.CreateParkingSlotCommand;
import com.spotfinderbackend.parkingmonitoring.interfaces.rest.resources.CreateParkingSlotResource;

public class CreateParkingSlotCommandFromResourceAssembler {

    public static CreateParkingSlotCommand toCommand(CreateParkingSlotResource resource) {
        return new CreateParkingSlotCommand(
                resource.code()
        );
    }
}