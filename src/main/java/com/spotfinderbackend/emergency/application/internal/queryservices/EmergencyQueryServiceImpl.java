package com.spotfinderbackend.emergency.application.internal.queryservices;

import com.spotfinderbackend.emergency.domain.model.aggregates.EmergencyAlert;
import com.spotfinderbackend.emergency.domain.model.exceptions.NoActiveEmergencyException;
import com.spotfinderbackend.emergency.domain.model.queries.GetEmergencyStatusQuery;
import com.spotfinderbackend.emergency.domain.model.valueobjects.EmergencyStatus;
import com.spotfinderbackend.emergency.domain.services.EmergencyQueryService;
import com.spotfinderbackend.emergency.infrastructure.persistence.jpa.repositories.EmergencyRepository;
import org.springframework.stereotype.Service;

@Service
public class EmergencyQueryServiceImpl implements EmergencyQueryService {

    private final EmergencyRepository repository;

    public EmergencyQueryServiceImpl(EmergencyRepository repository) {
        this.repository = repository;
    }

    @Override
    public EmergencyAlert handle(GetEmergencyStatusQuery query) {

        return repository.findByStatus(EmergencyStatus.ACTIVE)
                .orElseThrow(NoActiveEmergencyException::new);
    }
}