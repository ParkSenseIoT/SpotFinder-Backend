package com.spotfinderbackend.reservations.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class ReservationExpiredEvent extends ApplicationEvent {
    private final Long reservationId;
    private final Long userId;
    private final Long slotId;
    private final LocalDateTime expiredAt;

    public ReservationExpiredEvent(Object source, Long reservationId, Long userId, Long slotId,
                                   LocalDateTime expiredAt) {
        super(source);
        this.reservationId = reservationId;
        this.userId = userId;
        this.slotId = slotId;
        this.expiredAt = expiredAt;
    }
}
