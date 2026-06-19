package com.spotfinderbackend.reservations.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class ReservationCancelledEvent extends ApplicationEvent {
    private final Long reservationId;
    private final Long userId;
    private final Long slotId;
    private final String reason;
    private final LocalDateTime cancelledAt;

    public ReservationCancelledEvent(Object source, Long reservationId, Long userId, Long slotId,
                                     String reason, LocalDateTime cancelledAt) {
        super(source);
        this.reservationId = reservationId;
        this.userId = userId;
        this.slotId = slotId;
        this.reason = reason;
        this.cancelledAt = cancelledAt;
    }
}
