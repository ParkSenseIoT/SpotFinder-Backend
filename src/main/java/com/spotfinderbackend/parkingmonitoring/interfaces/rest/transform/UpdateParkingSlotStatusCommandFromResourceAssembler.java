package com.spotfinderbackend.parkingmonitoring.interfaces.rest.transform;

import com.spotfinderbackend.parkingmonitoring.domain.model.commands.UpdateParkingSlotStatusCommand;
import com.spotfinderbackend.parkingmonitoring.interfaces.rest.resources.UpdateParkingSlotStatusResource;

public class UpdateParkingSlotStatusCommandFromResourceAssembler {

    public static UpdateParkingSlotStatusCommand toCommand(Long slotId, UpdateParkingSlotStatusResource resource) {
        return new UpdateParkingSlotStatusCommand(
                slotId,
                resource.status()
        );
    }
}