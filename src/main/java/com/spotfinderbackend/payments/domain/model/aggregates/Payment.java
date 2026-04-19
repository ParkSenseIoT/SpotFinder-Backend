package com.spotfinderbackend.payments.domain.model.aggregates;

import com.spotfinderbackend.payments.domain.model.exceptions.PaymentFailedException;
import com.spotfinderbackend.payments.domain.model.valueobjects.PaymentAmount;
import com.spotfinderbackend.payments.domain.model.valueobjects.PaymentMethod;
import com.spotfinderbackend.payments.domain.model.valueobjects.PaymentStatus;
import com.spotfinderbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Payment extends AuditableAbstractAggregateRoot<Payment> {

    private Long parkingSessionId;

    @Embedded
    private PaymentAmount amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private LocalDateTime paidAt;

    protected Payment() {}

    public Payment(Long parkingSessionId, PaymentAmount amount, PaymentMethod method) {
        this.parkingSessionId = parkingSessionId;
        this.amount = amount;
        this.method = method;
        this.status = PaymentStatus.PENDING;
    }

    // DOMAIN LOGIC

    public void markAsSuccess() {
        this.status = PaymentStatus.SUCCESS;
        this.paidAt = LocalDateTime.now();
    }

    public void markAsFailed() {
        this.status = PaymentStatus.FAILED;
        throw new PaymentFailedException();
    }

    public boolean isSuccessful() {
        return this.status == PaymentStatus.SUCCESS;
    }
}