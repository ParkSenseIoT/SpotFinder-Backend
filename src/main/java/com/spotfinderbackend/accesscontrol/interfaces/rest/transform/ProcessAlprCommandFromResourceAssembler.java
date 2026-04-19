package com.spotfinderbackend.accesscontrol.interfaces.rest.transform;

import com.spotfinderbackend.accesscontrol.domain.model.commands.ProcessAlprCommand;
import com.spotfinderbackend.accesscontrol.interfaces.rest.resources.ProcessAlprResource;

public class ProcessAlprCommandFromResourceAssembler {

    public static ProcessAlprCommand toCommand(ProcessAlprResource resource) {
        return new ProcessAlprCommand(resource.imageBase64());
    }
}