package com.spotfinderbackend.vehicles.domain.model.exceptions;

public class DuplicatePlateException extends RuntimeException {
    public DuplicatePlateException(String message) {
        super(message);
    }
}
