package com.spotfinderbackend.parkingsessions.domain.model.valueobjects;

import java.time.LocalDateTime;

public class TimeRange {

    private final LocalDateTime startTime;
    private LocalDateTime endTime;

    public TimeRange(LocalDateTime startTime) {
        if (startTime == null)
            throw new IllegalArgumentException("Start time cannot be null");

        this.startTime = startTime;
    }

    public void end(LocalDateTime endTime) {
        if (endTime == null || endTime.isBefore(startTime))
            throw new IllegalArgumentException("Invalid end time");

        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}