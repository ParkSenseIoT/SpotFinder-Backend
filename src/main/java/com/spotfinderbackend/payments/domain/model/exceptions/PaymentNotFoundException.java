package com.spotfinderbackend.payments.domain.model.exceptions;

import com.spotfinderbackend.shared.domain.model.exceptions.NotFoundException;

public class PaymentNotFoundException extends NotFoundException {

    public PaymentNotFoundException(Long id) {
        super("Payment with id " + id + " not found");
    }
}