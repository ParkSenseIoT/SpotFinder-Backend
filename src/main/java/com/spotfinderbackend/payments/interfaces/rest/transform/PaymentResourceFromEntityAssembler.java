package com.spotfinderbackend.payments.interfaces.rest.transform;

import com.spotfinderbackend.payments.domain.model.aggregates.Payment;
import com.spotfinderbackend.payments.interfaces.rest.resources.PaymentResource;

public class PaymentResourceFromEntityAssembler {

    public static PaymentResource toResource(Payment payment) {
        return new PaymentResource(
                payment.getId(),
                payment.getParkingSessionId(),
                payment.getAmount().value(),
                payment.getMethod().name(),
                payment.getStatus().name(),
                payment.getPaidAt()
        );
    }
}