package com.spotfinderbackend.reservations.application.internal.eventhandlers;

import com.spotfinderbackend.reservations.application.internal.outboundservices.acl.ExternalNotificationService;
import com.spotfinderbackend.reservations.domain.model.events.ReservationCancelledEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/** Notifies the driver that their reservation was cancelled (and acks the reason). */
@Service
public class ReservationCancelledEventHandler {

    private final ExternalNotificationService notificationService;

    public ReservationCancelledEventHandler(ExternalNotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @EventListener
    public void on(ReservationCancelledEvent event) {
        notificationService.notifyReservationCancelled(event.getUserId(),
                event.getReservationId(), event.getReason());
    }
}
