package com.spotfinderbackend.payments.domain.model.commands;

import com.spotfinderbackend.shared.domain.model.exceptions.BadRequestException;

public record ProcessYapePaymentCommand(
        Long paymentId,
        String phoneNumber
) {
    public ProcessYapePaymentCommand {
        if (paymentId == null || paymentId <= 0)
            throw new BadRequestException("PaymentId is required");

        if (phoneNumber == null || phoneNumber.isBlank())
            throw new BadRequestException("Phone number is required");
    }
}