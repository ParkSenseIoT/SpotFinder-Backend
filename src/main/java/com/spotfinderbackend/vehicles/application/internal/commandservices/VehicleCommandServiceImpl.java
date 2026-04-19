package com.spotfinderbackend.vehicles.application.internal.commandservices;

import com.spotfinderbackend.vehicles.domain.model.aggregates.Vehicle;
import com.spotfinderbackend.vehicles.domain.model.commands.CreateVehicleCommand;
import com.spotfinderbackend.vehicles.domain.model.commands.DeleteVehicleCommand;
import com.spotfinderbackend.vehicles.domain.model.exceptions.VehicleNotFoundException;
import com.spotfinderbackend.vehicles.domain.model.exceptions.VehicleOwnershipException;
import com.spotfinderbackend.vehicles.domain.model.valueobjects.Plate;
import com.spotfinderbackend.vehicles.domain.services.VehicleCommandService;
import com.spotfinderbackend.vehicles.infrastructure.persistence.jpa.repositories.VehicleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class VehicleCommandServiceImpl implements VehicleCommandService {

    private final VehicleRepository vehicleRepository;

    public VehicleCommandServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    @Transactional
    public Long handle(CreateVehicleCommand command) {

        Plate plate = new Plate(command.plate());

        // TS28: validar duplicado
        if (vehicleRepository.existsByPlate(plate)) {
            throw new com.spotfinderbackend.shared.domain.model.exceptions.BadRequestException(
                    "Vehicle with this plate already exists"
            );
        }

        Vehicle vehicle = new Vehicle(
                command.userId(),
                plate,
                command.brand(),
                command.model(),
                command.color()
        );

        vehicleRepository.save(vehicle);

        return vehicle.getId();
    }

    @Override
    @Transactional
    public void handle(DeleteVehicleCommand command) {

        Vehicle vehicle = vehicleRepository.findById(command.vehicleId())
                .orElseThrow(() -> new VehicleNotFoundException(command.vehicleId()));

        // VALIDACIÓN CLAVE (ownership)
        if (!vehicle.getUserId().equals(command.userId())) {
            throw new VehicleOwnershipException();
        }

        vehicleRepository.delete(vehicle);
    }
}