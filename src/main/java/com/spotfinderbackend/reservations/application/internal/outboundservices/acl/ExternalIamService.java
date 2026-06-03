package com.spotfinderbackend.reservations.application.internal.outboundservices.acl;

import com.spotfinderbackend.iam.interfaces.acl.IamContextFacade;
import org.springframework.stereotype.Service;

/**
 * Outbound ACL service used by Reservation BC to validate that the user
 * exists and is active before creating a reservation.
 */
@Service
public class ExternalIamService {

    private final IamContextFacade iam;

    public ExternalIamService(IamContextFacade iam) {
        this.iam = iam;
    }

    public boolean isActiveUser(Long userId) {
        return iam.findActiveUserId(userId).isPresent();
    }
}
