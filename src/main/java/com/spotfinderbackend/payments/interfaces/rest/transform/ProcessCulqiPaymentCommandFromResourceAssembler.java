package com.spotfinderbackend.payments.interfaces.rest.transform;

import com.spotfinderbackend.payments.domain.model.commands.ProcessCulqiPaymentCommand;
import com.spotfinderbackend.payments.interfaces.rest.resources.ProcessCulqiPaymentResource;

public class ProcessCulqiPaymentCommandFromResourceAssembler {

    public static ProcessCulqiPaymentCommand toCommand(Long paymentId, ProcessCulqiPaymentResource resource) {
        return new ProcessCulqiPaymentCommand(
                paymentId,
                resource.token()
        );
    }
}