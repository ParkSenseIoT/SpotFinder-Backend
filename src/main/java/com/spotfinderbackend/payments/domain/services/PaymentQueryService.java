package com.spotfinderbackend.payments.domain.services;

import com.spotfinderbackend.payments.domain.model.aggregates.Payment;
import com.spotfinderbackend.payments.domain.model.queries.GetPaymentByIdQuery;
import com.spotfinderbackend.payments.domain.model.queries.GetPaymentHistoryQuery;

import java.util.List;

public interface PaymentQueryService {

    Payment handle(GetPaymentByIdQuery query);

    List<Payment> handle(GetPaymentHistoryQuery query);
}