package com.spotfinderbackend.parkingsessions.domain.services;

import com.spotfinderbackend.parkingsessions.domain.model.aggregates.ParkingSession;
import com.spotfinderbackend.parkingsessions.domain.model.queries.*;

import java.util.List;

public interface ParkingSessionQueryService {

    ParkingSession handle(GetActiveSessionQuery query);

    ParkingSession handle(GetParkingSessionByIdQuery query);

    List<ParkingSession> handle(GetParkingSessionHistoryQuery query);
}