package com.spotfinderbackend.parkingsessions.domain.services;

import com.spotfinderbackend.parkingsessions.domain.model.commands.CreateParkingSessionCommand;
import com.spotfinderbackend.parkingsessions.domain.model.commands.EndParkingSessionCommand;

public interface ParkingSessionCommandService {

    Long handle(CreateParkingSessionCommand command);

    void handle(EndParkingSessionCommand command);
}