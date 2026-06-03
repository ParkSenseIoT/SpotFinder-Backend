package com.spotfinderbackend.reservations.domain.model.valueobjects;

/**
 * Lifecycle status of a parking-slot reservation.
 *
 * <ul>
 *   <li>{@code PENDING} — created and waiting for the driver to honor it within the grace period.</li>
 *   <li>{@code CONFIRMED} — the driver entered the facility within the grace period.</li>
 *   <li>{@code EXPIRED} — the grace period elapsed without confirmation (no-show).</li>
 *   <li>{@code CANCELLED} — the driver cancelled the reservation explicitly.</li>
 * </ul>
 */
public enum ReservationStatus {
    PENDING,
    CONFIRMED,
    EXPIRED,
    CANCELLED
}
