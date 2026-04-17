package com.spotfinderbackend.parkingsessions.application.internal.commandservices;

import com.spotfinderbackend.parkingsessions.domain.model.aggregates.ParkingSession;
import com.spotfinderbackend.parkingsessions.domain.model.commands.CreateParkingSessionCommand;
import com.spotfinderbackend.parkingsessions.domain.model.commands.EndParkingSessionCommand;
import com.spotfinderbackend.parkingsessions.domain.model.exceptions.SessionAlreadyActiveException;
import com.spotfinderbackend.parkingsessions.domain.model.exceptions.SessionNotFoundException;
import com.spotfinderbackend.parkingsessions.domain.model.valueobjects.SessionStatus;
import com.spotfinderbackend.parkingsessions.domain.services.ParkingSessionCommandService;
import com.spotfinderbackend.parkingsessions.infrastructure.persistence.jpa.repositories.ParkingSessionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ParkingSessionCommandServiceImpl implements ParkingSessionCommandService {

    private final ParkingSessionRepository repository;

    public ParkingSessionCommandServiceImpl(ParkingSessionRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Long handle(CreateParkingSessionCommand command) {

        // TS39 → validar sesión activa existente
        if (repository.existsByVehicleIdAndStatus(
                command.vehicleId(),
                SessionStatus.ACTIVE)) {

            throw new SessionAlreadyActiveException(command.vehicleId());
        }

        ParkingSession session = new ParkingSession(
                command.vehicleId(),
                command.parkingSlotId()
        );

        repository.save(session);

        return session.getId();
    }

    @Override
    @Transactional
    public void handle(EndParkingSessionCommand command) {

        ParkingSession session = repository.findById(command.sessionId())
                .orElseThrow(() -> new SessionNotFoundException(command.sessionId()));

        session.endSession(); // valida que esté PAID

        repository.save(session);
    }
}