package com.spotfinderbackend.iam.domain.model.exceptions;

import com.spotfinderbackend.shared.domain.model.exceptions.ConflictException;

/**
 * Exception thrown when attempting to create a user that already exists
 */
public class UserAlreadyExistsException extends ConflictException {

    public UserAlreadyExistsException(String email) {
        super("User with email " + email + " already exists");
    }
}
