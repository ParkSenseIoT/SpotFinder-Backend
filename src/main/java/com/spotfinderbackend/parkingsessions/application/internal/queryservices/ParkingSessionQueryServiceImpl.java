package com.spotfinderbackend.parkingsessions.application.internal.queryservices;

import com.spotfinderbackend.parkingsessions.domain.model.aggregates.ParkingSession;
import com.spotfinderbackend.parkingsessions.domain.model.exceptions.SessionNotFoundException;
import com.spotfinderbackend.parkingsessions.domain.model.queries.GetActiveSessionQuery;
import com.spotfinderbackend.parkingsessions.domain.model.queries.GetParkingSessionByIdQuery;
import com.spotfinderbackend.parkingsessions.domain.model.queries.GetParkingSessionHistoryQuery;
import com.spotfinderbackend.parkingsessions.domain.model.valueobjects.SessionStatus;
import com.spotfinderbackend.parkingsessions.domain.services.ParkingSessionQueryService;
import com.spotfinderbackend.parkingsessions.infrastructure.persistence.jpa.repositories.ParkingSessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSessionQueryServiceImpl implements ParkingSessionQueryService {

    private final ParkingSessionRepository repository;

    public ParkingSessionQueryServiceImpl(ParkingSessionRepository repository) {
        this.repository = repository;
    }

    @Override
    public ParkingSession handle(GetActiveSessionQuery query) {

        return repository.findByVehicleIdAndStatus(
                        query.vehicleId(),
                        SessionStatus.ACTIVE)
                .orElseThrow(() ->
                        new SessionNotFoundException(query.vehicleId()));
    }

    @Override
    public ParkingSession handle(GetParkingSessionByIdQuery query) {

        return repository.findById(query.sessionId())
                .orElseThrow(() ->
                        new SessionNotFoundException(query.sessionId()));
    }

    @Override
    public List<ParkingSession> handle(GetParkingSessionHistoryQuery query) {

        return repository.findByVehicleId(query.vehicleId());
    }
}