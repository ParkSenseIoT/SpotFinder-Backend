package com.spotfinderbackend.payments.application.internal.commandservices;

import com.spotfinderbackend.payments.domain.model.aggregates.Payment;
import com.spotfinderbackend.payments.domain.model.commands.CreatePaymentCommand;
import com.spotfinderbackend.payments.domain.model.commands.ProcessCulqiPaymentCommand;
import com.spotfinderbackend.payments.domain.model.commands.ProcessYapePaymentCommand;
import com.spotfinderbackend.payments.domain.model.exceptions.PaymentNotFoundException;
import com.spotfinderbackend.payments.domain.services.PaymentCommandService;
import com.spotfinderbackend.payments.infrastructure.persistence.jpa.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentCommandServiceImpl implements PaymentCommandService {

    private final PaymentRepository repository;

    public PaymentCommandServiceImpl(PaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Long handle(CreatePaymentCommand command) {

        var payment = new Payment(
                command.parkingSessionId(),
                command.amount(),
                command.method()
        );

        repository.save(payment);

        return payment.getId();
    }

    @Override
    public void handle(ProcessCulqiPaymentCommand command) {

        var payment = repository.findById(command.paymentId())
                .orElseThrow(() -> new PaymentNotFoundException(command.paymentId()));

        // MOCK Culqi (sin integración real aún)
        if (command.token() == null || command.token().isBlank()) {
            payment.markAsFailed();
        } else {
            payment.markAsSuccess();
        }

        repository.save(payment);
    }

    @Override
    public void handle(ProcessYapePaymentCommand command) {

        var payment = repository.findById(command.paymentId())
                .orElseThrow(() -> new PaymentNotFoundException(command.paymentId()));

        // MOCK Yape
        if (command.phoneNumber() == null || command.phoneNumber().isBlank()) {
            payment.markAsFailed();
        } else {
            payment.markAsSuccess();
        }

        repository.save(payment);
    }
}