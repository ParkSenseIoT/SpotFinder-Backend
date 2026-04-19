package com.spotfinderbackend.accesscontrol.domain.services;

import com.spotfinderbackend.accesscontrol.domain.model.commands.OpenAllBarriersCommand;
import com.spotfinderbackend.accesscontrol.domain.model.commands.ProcessAlprCommand;
import com.spotfinderbackend.accesscontrol.domain.model.commands.RegisterEntryCommand;
import com.spotfinderbackend.accesscontrol.domain.model.commands.RegisterExitCommand;
import com.spotfinderbackend.accesscontrol.domain.model.valueobjects.AlprResult;

public interface AccessControlCommandService {

    void handle(RegisterEntryCommand command);

    void handle(RegisterExitCommand command);

    AlprResult handle(ProcessAlprCommand command);

    void handle(OpenAllBarriersCommand command);
}