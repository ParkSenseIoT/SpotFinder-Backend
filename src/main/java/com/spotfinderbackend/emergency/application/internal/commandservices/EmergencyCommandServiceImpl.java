package com.spotfinderbackend.emergency.application.internal.commandservices;

import com.spotfinderbackend.accesscontrol.domain.model.commands.OpenAllBarriersCommand;
import com.spotfinderbackend.accesscontrol.domain.services.AccessControlCommandService;
import com.spotfinderbackend.emergency.domain.model.aggregates.EmergencyAlert;
import com.spotfinderbackend.emergency.domain.model.commands.*;
import com.spotfinderbackend.emergency.domain.model.exceptions.EmergencyNotFoundException;
import com.spotfinderbackend.emergency.domain.model.exceptions.NoActiveEmergencyException;
import com.spotfinderbackend.emergency.domain.model.valueobjects.EmergencyStatus;
import com.spotfinderbackend.emergency.domain.services.EmergencyCommandService;
import com.spotfinderbackend.emergency.infrastructure.persistence.jpa.repositories.EmergencyRepository;
import com.spotfinderbackend.notifications.domain.model.commands.CreateNotificationCommand;
import com.spotfinderbackend.notifications.domain.model.valueobjects.NotificationType;
import com.spotfinderbackend.notifications.domain.services.NotificationCommandService;
import com.spotfinderbackend.parkingmonitoring.domain.model.commands.SetEvacuationModeCommand;
import com.spotfinderbackend.parkingmonitoring.domain.services.ParkingSlotCommandService;
import org.springframework.stereotype.Service;

@Service
public class EmergencyCommandServiceImpl implements EmergencyCommandService {

    private final EmergencyRepository repository;

    private final AccessControlCommandService accessControlCommandService;
    private final ParkingSlotCommandService parkingSlotCommandService;
    private final NotificationCommandService notificationCommandService;

    public EmergencyCommandServiceImpl(
            EmergencyRepository repository,
            AccessControlCommandService accessControlCommandService,
            ParkingSlotCommandService parkingSlotCommandService,
            NotificationCommandService notificationCommandService
    ) {
        this.repository = repository;
        this.accessControlCommandService = accessControlCommandService;
        this.parkingSlotCommandService = parkingSlotCommandService;
        this.notificationCommandService = notificationCommandService;
    }

    // TS54 → CREATE ALERT
    @Override
    public Long handle(CreateEmergencyAlertCommand command) {

        var alert = new EmergencyAlert(
                command.description(),
                command.severity()
        );

        repository.save(alert);

        return alert.getId();
    }

    // TS56 → ACTIVATE EVACUATION
    @Override
    public void handle(ActivateEvacuationCommand command) {

        var activeAlert = repository.findByStatus(EmergencyStatus.ACTIVE)
                .orElseThrow(NoActiveEmergencyException::new);

        // 1. ABRIR TODAS LAS BARRERAS
        accessControlCommandService.handle(new OpenAllBarriersCommand());

        // 2. ACTIVAR MODO EVACUACIÓN EN PARKING
        parkingSlotCommandService.handle(new SetEvacuationModeCommand());

        // 3. NOTIFICACIÓN MASIVA (SIMPLIFICADO)
        notificationCommandService.handle(
                new CreateNotificationCommand(
                        0L, //  broadcast (luego mejora)
                        "EMERGENCY ALERT",
                        NotificationType.EMERGENCY,
                        "Evacuation protocol activated"
                )
        );
    }

    // TS57 → RESOLVE ALERT
    @Override
    public void handle(ResolveEmergencyCommand command) {

        var alert = repository.findById(command.alertId())
                .orElseThrow(() -> new EmergencyNotFoundException(command.alertId()));

        alert.resolve();

        repository.save(alert);
    }
}