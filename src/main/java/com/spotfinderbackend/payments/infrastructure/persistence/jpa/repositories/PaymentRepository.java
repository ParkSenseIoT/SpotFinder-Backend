package com.spotfinderbackend.payments.infrastructure.persistence.jpa.repositories;

import com.spotfinderbackend.payments.domain.model.aggregates.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByParkingSessionId(Long parkingSessionId);
}