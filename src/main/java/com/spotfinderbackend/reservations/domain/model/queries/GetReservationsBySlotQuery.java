package com.spotfinderbackend.reservations.domain.model.queries;

/**
 * Returns every reservation associated with a given slot, ordered by creation date desc.
 * Used by Parking Monitoring BC to know if a slot is currently held.
 */
public record GetReservationsBySlotQuery(Long slotId) { }
