package com.spotfinderbackend.parkingsessions.domain.model.exceptions;

public class SessionNotFoundException extends RuntimeException {
    public SessionNotFoundException(String message) {
        super(message);
    }
}
