package com.spotfinderbackend.payments.domain.model.commands;

import com.spotfinderbackend.payments.domain.model.valueobjects.PaymentAmount;
import com.spotfinderbackend.payments.domain.model.valueobjects.PaymentMethod;
import com.spotfinderbackend.shared.domain.model.exceptions.BadRequestException;

public record CreatePaymentCommand(
        Long parkingSessionId,
        PaymentAmount amount,
        PaymentMethod method
) {
    public CreatePaymentCommand {
        if (parkingSessionId == null || parkingSessionId <= 0)
            throw new BadRequestException("ParkingSessionId is required");

        if (amount == null)
            throw new BadRequestException("Amount is required");

        if (method == null)
            throw new BadRequestException("Payment method is required");
    }
}