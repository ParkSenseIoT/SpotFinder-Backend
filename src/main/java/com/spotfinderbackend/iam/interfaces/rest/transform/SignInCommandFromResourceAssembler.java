package com.spotfinderbackend.iam.interfaces.rest.transform;


import com.spotfinderbackend.iam.domain.model.commands.SignInCommand;
import com.spotfinderbackend.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandfromResource(SignInResource resource) {
        return new SignInCommand(resource.email(), resource.password());
    }
}
