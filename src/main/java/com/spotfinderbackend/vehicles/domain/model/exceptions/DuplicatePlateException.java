package com.spotfinderbackend.vehicles.domain.model.exceptions;

import com.spotfinderbackend.shared.domain.model.exceptions.BusinessRuleException;

public class DuplicatePlateException extends BusinessRuleException {
    public DuplicatePlateException(String plate) {
        super("Vehicle with plate " + plate + " already exists");
    }
}