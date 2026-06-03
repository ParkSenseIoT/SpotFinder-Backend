package com.spotfinderbackend.reservations.interfaces.rest.transform;

import com.spotfinderbackend.reservations.domain.model.commands.CreateReservationCommand;
import com.spotfinderbackend.reservations.interfaces.rest.resources.CreateReservationResource;

import java.time.LocalDateTime;

public class CreateReservationCommandFromResourceAssembler {

    public static CreateReservationCommand toCommandFromResource(CreateReservationResource resource) {
        return new CreateReservationCommand(
                resource.userId(),
                resource.slotId(),
                resource.reservedFrom() == null ? LocalDateTime.now() : resource.reservedFrom(),
                resource.gracePeriodMinutes()
        );
    }
}
