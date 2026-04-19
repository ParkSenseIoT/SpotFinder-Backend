package com.spotfinderbackend.accesscontrol.interfaces.rest.resources;

public record AlprResultResource(
        String plate,
        boolean recognized
) {}