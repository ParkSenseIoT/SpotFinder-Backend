package com.spotfinderbackend.parkingmonitoring.domain.services;

import com.spotfinderbackend.parkingmonitoring.domain.model.aggregates.ParkingSlot;
import com.spotfinderbackend.parkingmonitoring.domain.model.queries.*;

import java.util.List;

public interface ParkingSlotQueryService {

    List<ParkingSlot> handle(GetAllParkingSlotsQuery query);

    List<ParkingSlot> handle(GetAvailableParkingSlotsQuery query);

    List<ParkingSlot> handle(GetRecommendedParkingSlotsQuery query);
}