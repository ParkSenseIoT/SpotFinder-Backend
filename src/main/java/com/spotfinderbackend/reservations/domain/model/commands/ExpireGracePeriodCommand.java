package com.spotfinderbackend.reservations.domain.model.commands;

/**
 * Background command (triggered by the scheduler) that expires every PENDING reservation
 * whose grace period elapsed without confirmation. No parameters — sweeps the full table.
 */
public record ExpireGracePeriodCommand() { }
