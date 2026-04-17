package com.spotfinderbackend.parkingsessions.domain.model.exceptions;

public class InvalidSessionStateException extends RuntimeException {
    public InvalidSessionStateException(String message) {
        super(message);
    }
}
