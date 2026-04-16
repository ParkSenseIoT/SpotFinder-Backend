package com.spotfinderbackend.iam.infrastructure.authorization.sfs.annotations;

public final class AuthorizeRoles {

    private AuthorizeRoles() {}

    public static final String ADMIN = "hasRole('ADMIN')";
}