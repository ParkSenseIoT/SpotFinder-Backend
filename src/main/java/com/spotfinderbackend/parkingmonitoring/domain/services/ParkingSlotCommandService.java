package com.spotfinderbackend.parkingmonitoring.domain.services;

import com.spotfinderbackend.parkingmonitoring.domain.model.commands.CreateParkingSlotCommand;
import com.spotfinderbackend.parkingmonitoring.domain.model.commands.UpdateParkingSlotStatusCommand;

public interface ParkingSlotCommandService {

    Long handle(CreateParkingSlotCommand command);

    void handle(UpdateParkingSlotStatusCommand command);
}