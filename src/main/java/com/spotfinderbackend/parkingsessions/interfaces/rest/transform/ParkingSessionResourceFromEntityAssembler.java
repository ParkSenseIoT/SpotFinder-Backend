package com.spotfinderbackend.parkingsessions.interfaces.rest.transform;

import com.spotfinderbackend.parkingsessions.domain.model.aggregates.ParkingSession;
import com.spotfinderbackend.parkingsessions.interfaces.rest.resources.ParkingSessionResource;

public class ParkingSessionResourceFromEntityAssembler {

    public static ParkingSessionResource toResource(ParkingSession session) {
        return new ParkingSessionResource(
                session.getId(),
                session.getVehicleId(),
                session.getParkingSlotId(),
                session.getStartTime(),
                session.getEndTime(),
                session.getStatus().name()
        );
    }
}