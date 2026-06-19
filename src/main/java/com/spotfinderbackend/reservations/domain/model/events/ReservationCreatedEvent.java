package com.spotfinderbackend.reservations.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class ReservationCreatedEvent extends ApplicationEvent {
    private final Long reservationId;
    private final Long userId;
    private final Long slotId;
    private final LocalDateTime reservedFrom;
    private final LocalDateTime reservedUntil;

    public ReservationCreatedEvent(Object source, Long reservationId, Long userId, Long slotId,
                                   LocalDateTime reservedFrom, LocalDateTime reservedUntil) {
        super(source);
        this.reservationId = reservationId;
        this.userId = userId;
        this.slotId = slotId;
        this.reservedFrom = reservedFrom;
        this.reservedUntil = reservedUntil;
    }
}
