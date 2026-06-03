package com.spotfinderbackend.reservations.interfaces.rest.transform;

import com.spotfinderbackend.reservations.domain.model.aggregates.Reservation;
import com.spotfinderbackend.reservations.interfaces.rest.resources.ReservationResource;

import java.util.Collection;
import java.util.List;

public class ReservationResourceFromEntityAssembler {

    public static ReservationResource toResourceFromEntity(Reservation r) {
        return new ReservationResource(
                r.getId(),
                r.getUserId() == null ? null : r.getUserId().value(),
                r.getSlotId() == null ? null : r.getSlotId().value(),
                r.getStatus() == null ? null : r.getStatus().name(),
                r.getReservedFrom(),
                r.getReservedUntil(),
                r.getGracePeriodMinutes(),
                r.getConfirmedAt(),
                r.getCancelledAt(),
                r.getExpiredAt(),
                r.getCancellationReason(),
                r.getCreatedAt(),
                r.getUpdatedAt()
        );
    }

    public static List<ReservationResource> toResourcesFromEntities(Collection<Reservation> entities) {
        return entities.stream().map(ReservationResourceFromEntityAssembler::toResourceFromEntity).toList();
    }
}
