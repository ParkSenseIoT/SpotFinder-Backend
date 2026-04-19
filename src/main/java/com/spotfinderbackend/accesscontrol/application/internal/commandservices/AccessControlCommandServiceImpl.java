package com.spotfinderbackend.accesscontrol.application.internal.commandservices;

import com.spotfinderbackend.accesscontrol.domain.model.commands.*;
import com.spotfinderbackend.accesscontrol.domain.model.valueobjects.AlprResult;
import com.spotfinderbackend.accesscontrol.domain.services.AccessControlCommandService;
import com.spotfinderbackend.notifications.domain.model.commands.CreateNotificationCommand;
import com.spotfinderbackend.notifications.domain.model.valueobjects.NotificationType;
import com.spotfinderbackend.notifications.domain.services.NotificationCommandService;
import com.spotfinderbackend.parkingsessions.domain.model.commands.CreateParkingSessionCommand;
import com.spotfinderbackend.parkingsessions.domain.model.commands.EndParkingSessionCommand;
import com.spotfinderbackend.parkingsessions.domain.model.queries.GetActiveSessionQuery;
import com.spotfinderbackend.parkingsessions.domain.services.ParkingSessionCommandService;
import com.spotfinderbackend.parkingsessions.domain.services.ParkingSessionQueryService;
import com.spotfinderbackend.shared.domain.model.exceptions.BusinessRuleException;
import com.spotfinderbackend.shared.domain.model.exceptions.NotFoundException;
import com.spotfinderbackend.vehicles.domain.model.queries.GetVehicleByPlateQuery;
import com.spotfinderbackend.vehicles.domain.services.VehicleQueryService;
import org.springframework.stereotype.Service;

@Service
public class AccessControlCommandServiceImpl implements AccessControlCommandService {

    private final VehicleQueryService vehicleQueryService;
    private final ParkingSessionCommandService sessionCommandService;
    private final ParkingSessionQueryService sessionQueryService;
    private final NotificationCommandService notificationCommandService;

    public AccessControlCommandServiceImpl(
            VehicleQueryService vehicleQueryService,
            ParkingSessionCommandService sessionCommandService,
            ParkingSessionQueryService sessionQueryService,
            NotificationCommandService notificationCommandService
    ) {
        this.vehicleQueryService = vehicleQueryService;
        this.sessionCommandService = sessionCommandService;
        this.sessionQueryService = sessionQueryService;
        this.notificationCommandService = notificationCommandService;
    }

    // TS35 → ENTRY
    @Override
    public void handle(RegisterEntryCommand command) {

        var vehicle = vehicleQueryService
                .handle(new GetVehicleByPlateQuery(command.plate()))
                .orElseThrow(() ->
                        new NotFoundException("Vehicle with plate " + command.plate() + " not found")
                );

        var existingSession = sessionQueryService
                .handle(new GetActiveSessionQuery(vehicle.getId()));

        if (existingSession != null) {
            throw new BusinessRuleException("Vehicle already has an active session");
        }

        sessionCommandService.handle(
                new CreateParkingSessionCommand(
                        vehicle.getId(),
                        null
                )
        );

        notificationCommandService.handle(
                new CreateNotificationCommand(
                        vehicle.getUserId(),
                        "Ingreso confirmado",
                        NotificationType.ENTRY_CONFIRMED,
                        "Tu vehículo ha ingresado al estacionamiento"
                )
        );
    }

    // TS36 → EXIT
    @Override
    public void handle(RegisterExitCommand command) {

        var vehicle = vehicleQueryService
                .handle(new GetVehicleByPlateQuery(command.plate()))
                .orElseThrow(() ->
                        new NotFoundException("Vehicle with plate " + command.plate() + " not found")
                );

        var session = sessionQueryService
                .handle(new GetActiveSessionQuery(vehicle.getId()));

        if (session == null) {
            throw new NotFoundException("Active session not found for vehicle");
        }

        if (!session.getStatus().name().equals("PAID")) {
            throw new BusinessRuleException("Payment required before exit");
        }

        sessionCommandService.handle(
                new EndParkingSessionCommand(session.getId())
        );

        notificationCommandService.handle(
                new CreateNotificationCommand(
                        vehicle.getUserId(),
                        "Salida confirmada",
                        NotificationType.EXIT_CONFIRMED,
                        "Tu vehículo ha salido correctamente"
                )
        );
    }

    // TS37 → ALPR
    @Override
    public AlprResult handle(ProcessAlprCommand command) {

        // MOCK
        return new AlprResult("ABC-123", true);
    }
}