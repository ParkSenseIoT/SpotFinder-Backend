package com.spotfinderbackend.payments.application.internal.queryservices;

import com.spotfinderbackend.payments.domain.model.aggregates.Payment;
import com.spotfinderbackend.payments.domain.model.exceptions.PaymentNotFoundException;
import com.spotfinderbackend.payments.domain.model.queries.GetPaymentByIdQuery;
import com.spotfinderbackend.payments.domain.model.queries.GetPaymentHistoryQuery;
import com.spotfinderbackend.payments.domain.services.PaymentQueryService;
import com.spotfinderbackend.payments.infrastructure.persistence.jpa.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentQueryServiceImpl implements PaymentQueryService {

    private final PaymentRepository repository;

    public PaymentQueryServiceImpl(PaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Payment handle(GetPaymentByIdQuery query) {
        return repository.findById(query.paymentId())
                .orElseThrow(() -> new PaymentNotFoundException(query.paymentId()));
    }

    @Override
    public List<Payment> handle(GetPaymentHistoryQuery query) {
        return repository.findByParkingSessionId(query.parkingSessionId());
    }
}