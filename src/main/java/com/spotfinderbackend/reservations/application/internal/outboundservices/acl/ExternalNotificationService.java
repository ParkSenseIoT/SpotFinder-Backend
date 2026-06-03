package com.spotfinderbackend.reservations.application.internal.outboundservices.acl;

import com.spotfinderbackend.notifications.interfaces.acl.NotificationContextFacade;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Outbound ACL service used by Reservation BC to push notifications via the
 * Notification Management BC. Keeps Reservation decoupled from FCM templates.
 */
@Service
public class ExternalNotificationService {

    private final NotificationContextFacade notifications;

    public ExternalNotificationService(NotificationContextFacade notifications) {
        this.notifications = notifications;
    }

    public void notifyReservationCreated(Long userId, Long reservationId, Long slotId) {
        notifications.sendNotification(
                userId,
                "RESERVATION_CREATED",
                "Reserva confirmada",
                "Tu espacio quedó reservado. Tienes 15 minutos para llegar al estacionamiento.",
                Map.of("reservationId", String.valueOf(reservationId),
                        "slotId", String.valueOf(slotId))
        );
    }

    public void notifyReservationExpired(Long userId, Long reservationId) {
        notifications.sendNotification(
                userId,
                "RESERVATION_EXPIRED",
                "Reserva expirada",
                "Tu reserva expiró porque no se registró tu ingreso dentro de la ventana de gracia.",
                Map.of("reservationId", String.valueOf(reservationId))
        );
    }

    public void notifyReservationCancelled(Long userId, Long reservationId, String reason) {
        notifications.sendNotification(
                userId,
                "RESERVATION_CANCELLED",
                "Reserva cancelada",
                "Tu reserva fue cancelada" + (reason == null || reason.isBlank() ? "." : ": " + reason),
                Map.of("reservationId", String.valueOf(reservationId))
        );
    }
}
