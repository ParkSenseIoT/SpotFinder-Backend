package com.spotfinderbackend.reservations.domain.model.aggregates;

import com.spotfinderbackend.reservations.domain.model.commands.CreateReservationCommand;
import com.spotfinderbackend.reservations.domain.model.events.ReservationCancelledEvent;
import com.spotfinderbackend.reservations.domain.model.events.ReservationConfirmedEvent;
import com.spotfinderbackend.reservations.domain.model.events.ReservationCreatedEvent;
import com.spotfinderbackend.reservations.domain.model.events.ReservationExpiredEvent;
import com.spotfinderbackend.reservations.domain.model.valueobjects.ReservationSlotId;
import com.spotfinderbackend.reservations.domain.model.valueobjects.ReservationStatus;
import com.spotfinderbackend.reservations.domain.model.valueobjects.ReservationUserId;
import com.spotfinderbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.spotfinderbackend.shared.domain.model.exceptions.BusinessRuleException;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Reservation Aggregate Root — Reservation Management Bounded Context.
 *
 * <p>Encapsulates the lifecycle of a single parking-slot reservation: PENDING → CONFIRMED,
 * or PENDING → CANCELLED, or PENDING → EXPIRED (no-show). Once a reservation leaves PENDING
 * its terminal status is immutable.</p>
 *
 * <p>Available only for users on Pro/Premium plans (plan validation happens upstream in
 * Application Layer; this aggregate only enforces the temporal/state rules).</p>
 */
@Entity
@Getter
public class Reservation extends AuditableAbstractAggregateRoot<Reservation> {

    @Embedded
    private ReservationUserId userId;

    @Embedded
    private ReservationSlotId slotId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private ReservationStatus status;

    @Column(nullable = false)
    private LocalDateTime reservedFrom;

    @Column(nullable = false)
    private LocalDateTime reservedUntil;

    @Column(nullable = false)
    private int gracePeriodMinutes;

    @Column
    private LocalDateTime confirmedAt;

    @Column
    private LocalDateTime cancelledAt;

    @Column
    private LocalDateTime expiredAt;

    @Column(length = 255)
    private String cancellationReason;

    protected Reservation() {}

    public Reservation(CreateReservationCommand command) {
        this.userId = new ReservationUserId(command.userId());
        this.slotId = new ReservationSlotId(command.slotId());
        this.status = ReservationStatus.PENDING;
        this.reservedFrom = command.reservedFrom();
        this.gracePeriodMinutes = command.effectiveGracePeriodMinutes();
        this.reservedUntil = command.reservedFrom().plusMinutes(gracePeriodMinutes);
        registerEvent(new ReservationCreatedEvent(this, null,
                userId.value(), slotId.value(), reservedFrom, reservedUntil));
    }

    /** Mark as honored. Called by Access Control BC when ALPR matches a reservation. */
    public void confirm() {
        if (status != ReservationStatus.PENDING)
            throw new BusinessRuleException("Reservation can only be confirmed while PENDING (current: " + status + ").");
        if (LocalDateTime.now().isAfter(reservedUntil))
            throw new BusinessRuleException("Reservation grace period already elapsed.");
        this.status = ReservationStatus.CONFIRMED;
        this.confirmedAt = LocalDateTime.now();
        registerEvent(new ReservationConfirmedEvent(this, getId(), userId.value(), slotId.value(), confirmedAt));
    }

    /** Driver/admin cancellation. Only PENDING reservations can be cancelled by the user. */
    public void cancel(String reason) {
        if (status != ReservationStatus.PENDING)
            throw new BusinessRuleException("Only PENDING reservations can be cancelled (current: " + status + ").");
        this.status = ReservationStatus.CANCELLED;
        this.cancelledAt = LocalDateTime.now();
        this.cancellationReason = reason;
        registerEvent(new ReservationCancelledEvent(this, getId(), userId.value(), slotId.value(), reason, cancelledAt));
    }

    /** Triggered automatically by the scheduler when the grace period elapses without confirmation. */
    public void expire() {
        if (status != ReservationStatus.PENDING) return;
        this.status = ReservationStatus.EXPIRED;
        this.expiredAt = LocalDateTime.now();
        registerEvent(new ReservationExpiredEvent(this, getId(), userId.value(), slotId.value(), expiredAt));
    }

    public boolean isPending()   { return status == ReservationStatus.PENDING; }
    public boolean isConfirmed() { return status == ReservationStatus.CONFIRMED; }
    public boolean isCancelled() { return status == ReservationStatus.CANCELLED; }
    public boolean isExpired()   { return status == ReservationStatus.EXPIRED; }
    public boolean isActive()    { return isPending() || isConfirmed(); }

    /** True when the grace period elapsed without confirmation. */
    public boolean shouldExpireNow(LocalDateTime now) {
        return isPending() && now.isAfter(reservedUntil);
    }
}
