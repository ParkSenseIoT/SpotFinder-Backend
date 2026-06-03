package com.spotfinderbackend.reservations.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class ReservationConfirmedEvent extends ApplicationEvent {
    private final Long reservationId;
    private final Long userId;
    private final Long slotId;
    private final LocalDateTime confirmedAt;

    public ReservationConfirmedEvent(Object source, Long reservationId, Long userId, Long slotId,
                                     LocalDateTime confirmedAt) {
        super(source);
        this.reservationId = reservationId;
        this.userId = userId;
        this.slotId = slotId;
        this.confirmedAt = confirmedAt;
    }
}
