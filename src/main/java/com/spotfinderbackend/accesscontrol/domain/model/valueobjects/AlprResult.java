package com.spotfinderbackend.accesscontrol.domain.model.valueobjects;

public record AlprResult(
        String plate,
        boolean recognized
) {}