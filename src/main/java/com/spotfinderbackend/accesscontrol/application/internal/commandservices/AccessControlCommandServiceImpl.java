package com.spotfinderbackend.accesscontrol.application.internal.commandservices;

import com.spotfinderbackend.accesscontrol.domain.model.commands.*;
import com.spotfinderbackend.accesscontrol.domain.model.valueobjects.AlprResult;
import com.spotfinderbackend.accesscontrol.domain.services.AccessControlCommandService;
import com.spotfinderbackend.notifications.domain.model.commands.CreateNotificationCommand;
import com.spotfinderbackend.notifications.domain.services.NotificationCommandService;
import com.spotfinderbackend.parkingsessions.domain.model.commands.CreateParkingSessionCommand;
import com.spotfinderbackend.parkingsessions.domain.model.commands.EndParkingSessionCommand;
import com.spotfinderbackend.parkingsessions.domain.model.queries.GetActiveSessionQuery;
import com.spotfinderbackend.parkingsessions.domain.services.ParkingSessionCommandService;
import com.spotfinderbackend.parkingsessions.domain.services.ParkingSessionQueryService;
import com.spotfinderbackend.shared.domain.model.exceptions.BusinessRuleException;
import com.spotfinderbackend.shared.domain.model.exceptions.NotFoundException;
import com.spotfinderbackend.vehicles.domain.model.aggregates.Vehicle;
import org.springframework.stereotype.Service;

@Service
public class AccessControlCommandServiceImpl implements AccessControlCommandService {

    private final ParkingSessionCommandService sessionCommandService;
    private final ParkingSessionQueryService sessionQueryService;
    private final NotificationCommandService notificationCommandService;

    public AccessControlCommandServiceImpl(

            ParkingSessionCommandService sessionCommandService,
            ParkingSessionQueryService sessionQueryService,
            NotificationCommandService notificationCommandService
    ) {
        this.sessionCommandService = sessionCommandService;
        this.sessionQueryService = sessionQueryService;
        this.notificationCommandService = notificationCommandService;
    }


    @Override
    public void handle(RegisterEntryCommand command) {

    }

    @Override
    public void handle(RegisterExitCommand command) {

    }

    @Override
    public AlprResult handle(ProcessAlprCommand command) {
        return null;
    }
}