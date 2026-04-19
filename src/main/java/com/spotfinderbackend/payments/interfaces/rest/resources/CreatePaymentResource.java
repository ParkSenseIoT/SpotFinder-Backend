package com.spotfinderbackend.payments.interfaces.rest.resources;

import com.spotfinderbackend.payments.domain.model.valueobjects.PaymentMethod;

import java.math.BigDecimal;

public record CreatePaymentResource(
        Long parkingSessionId,
        BigDecimal amount,
        PaymentMethod method
) {}