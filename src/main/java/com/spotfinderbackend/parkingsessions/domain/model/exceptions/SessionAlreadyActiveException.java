package com.spotfinderbackend.parkingsessions.domain.model.exceptions;

public class SessionAlreadyActiveException extends RuntimeException {
    public SessionAlreadyActiveException(String message) {
        super(message);
    }
}
