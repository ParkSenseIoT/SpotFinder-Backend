package com.spotfinderbackend.emergency.domain.services;

import com.spotfinderbackend.emergency.domain.model.commands.CreateEmergencyAlertCommand;
import com.spotfinderbackend.emergency.domain.model.commands.ActivateEvacuationCommand;
import com.spotfinderbackend.emergency.domain.model.commands.ResolveEmergencyCommand;

public interface EmergencyCommandService {

    Long handle(CreateEmergencyAlertCommand command);

    void handle(ActivateEvacuationCommand command);

    void handle(ResolveEmergencyCommand command);
}