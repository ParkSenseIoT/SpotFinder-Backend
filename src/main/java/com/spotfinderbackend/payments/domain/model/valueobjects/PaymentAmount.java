package com.spotfinderbackend.payments.domain.model.valueobjects;

import com.spotfinderbackend.shared.domain.model.exceptions.BadRequestException;

import java.math.BigDecimal;

public record PaymentAmount(BigDecimal value) {

    public PaymentAmount {
        if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Payment amount must be greater than zero");
        }
    }
}