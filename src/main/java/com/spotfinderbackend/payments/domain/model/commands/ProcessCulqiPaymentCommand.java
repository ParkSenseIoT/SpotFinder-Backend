package com.spotfinderbackend.payments.domain.model.commands;

import com.spotfinderbackend.shared.domain.model.exceptions.BadRequestException;

public record ProcessCulqiPaymentCommand(
        Long paymentId,
        String token
) {
    public ProcessCulqiPaymentCommand {
        if (paymentId == null || paymentId <= 0)
            throw new BadRequestException("PaymentId is required");

        if (token == null || token.isBlank())
            throw new BadRequestException("Culqi token is required");
    }
}