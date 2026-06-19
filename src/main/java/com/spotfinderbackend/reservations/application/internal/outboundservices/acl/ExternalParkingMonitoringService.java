package com.spotfinderbackend.reservations.application.internal.outboundservices.acl;

import com.spotfinderbackend.parkingmonitoring.interfaces.acl.ParkingMonitoringContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Outbound ACL service used by Reservation BC to query Parking Monitoring BC.
 * Wraps {@link ParkingMonitoringContextFacade} so the rest of Reservation BC does not
 * depend on Parking Monitoring's internal model.
 */
@Service
public class ExternalParkingMonitoringService {

    private final ParkingMonitoringContextFacade parkingMonitoring;

    public ExternalParkingMonitoringService(ParkingMonitoringContextFacade parkingMonitoring) {
        this.parkingMonitoring = parkingMonitoring;
    }

    /** True if a slot with the given id exists in the Parking Monitoring BC. */
    public boolean slotExists(Long slotId) {
        return parkingMonitoring.findSlot(slotId).isPresent();
    }

    /** Snapshot of the slot status (AVAILABLE / OCCUPIED / OUT_OF_SERVICE). */
    public Optional<String> getSlotStatus(Long slotId) {
        return parkingMonitoring.findSlot(slotId)
                .map(ParkingMonitoringContextFacade.SlotSnapshot::status);
    }
}
