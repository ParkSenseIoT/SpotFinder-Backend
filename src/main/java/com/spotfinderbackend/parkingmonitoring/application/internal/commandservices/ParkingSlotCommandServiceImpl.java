package com.spotfinderbackend.parkingmonitoring.application.internal.commandservices;

import com.spotfinderbackend.parkingmonitoring.domain.model.aggregates.ParkingSlot;
import com.spotfinderbackend.parkingmonitoring.domain.model.commands.CreateParkingSlotCommand;
import com.spotfinderbackend.parkingmonitoring.domain.model.commands.SetEvacuationModeCommand;
import com.spotfinderbackend.parkingmonitoring.domain.model.commands.UpdateParkingSlotStatusCommand;
import com.spotfinderbackend.parkingmonitoring.domain.model.exceptions.*;
import com.spotfinderbackend.parkingmonitoring.domain.model.valueobjects.ParkingSlotCode;
import com.spotfinderbackend.parkingmonitoring.domain.services.ParkingSlotCommandService;
import com.spotfinderbackend.parkingmonitoring.infrastructure.persistence.jpa.repositories.ParkingSlotRepository;
import org.springframework.stereotype.Service;

@Service
public class ParkingSlotCommandServiceImpl implements ParkingSlotCommandService {

    private final ParkingSlotRepository repository;

    public ParkingSlotCommandServiceImpl(ParkingSlotRepository repository) {
        this.repository = repository;
    }

    @Override
    public Long handle(CreateParkingSlotCommand command) {

        var code = new ParkingSlotCode(command.code());

        if (repository.existsByCode(code)) {
            throw new ParkingSlotAlreadyExistsException(command.code());
        }

        var slot = new ParkingSlot(code);

        repository.save(slot);

        return slot.getId();
    }

    @Override
    public void handle(UpdateParkingSlotStatusCommand command) {

        var slot = repository.findById(command.slotId())
                .orElseThrow(() -> new ParkingSlotNotFoundException(command.slotId()));

        var newStatus = command.status();

        // lógica simple de transición
        switch (newStatus) {
            case AVAILABLE -> slot.markAsAvailable();
            case OCCUPIED -> slot.markAsOccupied();
            case OUT_OF_SERVICE -> slot.markAsOutOfService();
            default -> throw new InvalidParkingSlotStatusException();
        }

        repository.save(slot);
    }

    @Override
    public void handle(SetEvacuationModeCommand command) {
        // SIMULACIÓN
        System.out.println("Parking in EVACUATION MODE (LEDs RED)");
    }
}