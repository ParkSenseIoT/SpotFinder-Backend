package com.spotfinderbackend.accesscontrol.interfaces.rest.transform;

import com.spotfinderbackend.accesscontrol.domain.model.valueobjects.AlprResult;
import com.spotfinderbackend.accesscontrol.interfaces.rest.resources.AlprResultResource;

public class AlprResultResourceFromEntityAssembler {

    public static AlprResultResource toResource(AlprResult result) {
        return new AlprResultResource(
                result.plate(),
                result.recognized()
        );
    }
}