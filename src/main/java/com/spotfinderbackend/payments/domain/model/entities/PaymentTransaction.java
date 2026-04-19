package com.spotfinderbackend.payments.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class PaymentTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String provider; // CULQI / YAPE / MOCK

    private String externalReference;

    private LocalDateTime timestamp;

    protected PaymentTransaction() {}

    public PaymentTransaction(String provider, String externalReference) {
        this.provider = provider;
        this.externalReference = externalReference;
        this.timestamp = LocalDateTime.now();
    }
}