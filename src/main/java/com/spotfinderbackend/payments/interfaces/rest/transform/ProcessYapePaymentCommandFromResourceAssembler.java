package com.spotfinderbackend.payments.interfaces.rest.transform;

import com.spotfinderbackend.payments.domain.model.commands.ProcessYapePaymentCommand;
import com.spotfinderbackend.payments.interfaces.rest.resources.ProcessYapePaymentResource;

public class ProcessYapePaymentCommandFromResourceAssembler {

    public static ProcessYapePaymentCommand toCommand(Long paymentId, ProcessYapePaymentResource resource) {
        return new ProcessYapePaymentCommand(
                paymentId,
                resource.phoneNumber()
        );
    }
}