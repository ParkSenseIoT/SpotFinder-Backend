package com.spotfinderbackend.parkingmonitoring.interfaces.rest.transform;

import com.spotfinderbackend.parkingmonitoring.domain.model.aggregates.ParkingSlot;
import com.spotfinderbackend.parkingmonitoring.interfaces.rest.resources.ParkingSlotResource;

public class ParkingSlotResourceFromEntityAssembler {

    public static ParkingSlotResource toResource(ParkingSlot slot) {
        return new ParkingSlotResource(
                slot.getId(),
                slot.getCode().value(),
                slot.getStatus().name()
        );
    }
}