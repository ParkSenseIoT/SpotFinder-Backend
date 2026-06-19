package com.spotfinderbackend.reservations.application.internal.eventhandlers;

import com.spotfinderbackend.reservations.application.internal.outboundservices.acl.ExternalNotificationService;
import com.spotfinderbackend.reservations.domain.model.events.ReservationExpiredEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/** Notifies the driver that their reservation expired (no-show). */
@Service
public class ReservationExpiredEventHandler {

    private final ExternalNotificationService notificationService;

    public ReservationExpiredEventHandler(ExternalNotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @EventListener
    public void on(ReservationExpiredEvent event) {
        notificationService.notifyReservationExpired(event.getUserId(), event.getReservationId());
    }
}
