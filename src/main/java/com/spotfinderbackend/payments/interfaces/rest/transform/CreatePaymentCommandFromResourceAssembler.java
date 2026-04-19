package com.spotfinderbackend.payments.interfaces.rest.transform;

import com.spotfinderbackend.payments.domain.model.commands.CreatePaymentCommand;
import com.spotfinderbackend.payments.domain.model.valueobjects.PaymentAmount;
import com.spotfinderbackend.payments.domain.model.valueobjects.PaymentMethod;
import com.spotfinderbackend.payments.interfaces.rest.resources.CreatePaymentResource;

public class CreatePaymentCommandFromResourceAssembler {

    public static CreatePaymentCommand toCommand(CreatePaymentResource resource) {
        return new CreatePaymentCommand(
                resource.parkingSessionId(),
                new PaymentAmount(resource.amount()),
                resource.method()
        );
    }
}