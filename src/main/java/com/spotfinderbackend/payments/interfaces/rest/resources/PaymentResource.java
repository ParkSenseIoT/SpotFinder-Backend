package com.spotfinderbackend.payments.interfaces.rest.resources;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentResource(
        Long id,
        Long parkingSessionId,
        BigDecimal amount,
        String method,
        String status,
        LocalDateTime paidAt
) {}