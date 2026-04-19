package com.spotfinderbackend.accesscontrol.interfaces.rest.transform;

import com.spotfinderbackend.accesscontrol.domain.model.commands.RegisterEntryCommand;
import com.spotfinderbackend.accesscontrol.interfaces.rest.resources.RegisterEntryResource;

public class RegisterEntryCommandFromResourceAssembler {

    public static RegisterEntryCommand toCommand(RegisterEntryResource resource) {
        return new RegisterEntryCommand(resource.plate());
    }
}