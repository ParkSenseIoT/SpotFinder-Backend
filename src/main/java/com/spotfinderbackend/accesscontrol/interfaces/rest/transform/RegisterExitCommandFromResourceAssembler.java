package com.spotfinderbackend.accesscontrol.interfaces.rest.transform;

import com.spotfinderbackend.accesscontrol.domain.model.commands.RegisterExitCommand;
import com.spotfinderbackend.accesscontrol.interfaces.rest.resources.RegisterExitResource;

public class RegisterExitCommandFromResourceAssembler {

    public static RegisterExitCommand toCommand(RegisterExitResource resource) {
        return new RegisterExitCommand(resource.plate());
    }
}