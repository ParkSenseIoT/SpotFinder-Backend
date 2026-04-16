package com.spotfinderbackend.iam.interfaces.rest.resources;

public record SignInResource(
    String email,
    String password
) {
    
}
