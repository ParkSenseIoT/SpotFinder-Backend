package com.spotfinderbackend.reservations.application.internal.eventhandlers;

import com.spotfinderbackend.reservations.application.internal.outboundservices.acl.ExternalNotificationService;
import com.spotfinderbackend.reservations.domain.model.events.ReservationCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/** Sends the "reservation confirmed" push notification after a reservation is created. */
@Service
public class ReservationCreatedEventHandler {

    private final ExternalNotificationService notificationService;

    public ReservationCreatedEventHandler(ExternalNotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @EventListener
    public void on(ReservationCreatedEvent event) {
        notificationService.notifyReservationCreated(event.getUserId(),
                event.getReservationId(), event.getSlotId());
    }
}
