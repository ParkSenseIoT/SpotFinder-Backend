package com.spotfinderbackend.emergency.interfaces.rest.transform;

import com.spotfinderbackend.emergency.domain.model.commands.CreateEmergencyAlertCommand;
import com.spotfinderbackend.emergency.interfaces.rest.resources.CreateEmergencyAlertResource;

public class CreateEmergencyAlertCommandFromResourceAssembler {

    public static CreateEmergencyAlertCommand toCommand(CreateEmergencyAlertResource resource) {
        return new CreateEmergencyAlertCommand(
                resource.description(),
                resource.severity()
        );
    }
}