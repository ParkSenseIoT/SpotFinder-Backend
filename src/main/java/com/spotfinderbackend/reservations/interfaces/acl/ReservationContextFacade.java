package com.spotfinderbackend.reservations.interfaces.acl;

import java.util.Optional;

/**
 * Anti-Corruption Layer facade exposed by Reservation Management BC.
 * <p>
 * Consumed primarily by Access Control BC during ALPR entry: if an active reservation
 * exists for the recognized plate's user, Access Control calls
 * {@link #confirmIfActiveReservationFor(Long)} to honor it; otherwise the user enters
 * as a regular drop-in.
 * </p>
 * <p>Also consumed by Parking Monitoring BC to know if a slot is currently held.</p>
 */
public interface ReservationContextFacade {

    record ActiveReservationView(Long reservationId, Long userId, Long slotId,
                                 java.time.LocalDateTime reservedFrom,
                                 java.time.LocalDateTime reservedUntil) { }

    /** Returns the first active reservation for the user, if any. */
    Optional<ActiveReservationView> findActiveReservationForUser(Long userId);

    /**
     * If the user has an active reservation, confirm it (mark as honored) and return the
     * confirmed reservation id. Used by Access Control upon successful ALPR entry.
     */
    Optional<Long> confirmIfActiveReservationFor(Long userId);

    /** True if the slot currently has a PENDING reservation. */
    boolean isSlotReserved(Long slotId);
}
