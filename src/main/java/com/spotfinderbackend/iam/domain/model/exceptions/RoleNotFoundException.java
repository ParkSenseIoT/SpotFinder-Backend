package com.spotfinderbackend.iam.domain.model.exceptions;

import com.spotfinderbackend.iam.domain.model.valueobjects.Roles;
import com.spotfinderbackend.shared.domain.model.exceptions.NotFoundException;

public class RoleNotFoundException extends NotFoundException {
    public RoleNotFoundException(Roles requestedRole) {
        super("Role " + requestedRole + " not found");
    }
}
