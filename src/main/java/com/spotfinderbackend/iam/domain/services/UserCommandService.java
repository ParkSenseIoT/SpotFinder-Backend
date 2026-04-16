package com.spotfinderbackend.iam.domain.services;

import com.spotfinderbackend.iam.domain.model.commands.SignInCommand;
import com.spotfinderbackend.iam.domain.model.commands.SignUpCommand;

public interface UserCommandService {
    void handle(SignUpCommand command);

    void handle(SignInCommand command);
}
