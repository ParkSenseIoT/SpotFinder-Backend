package com.spotfinderbackend.analytics.application.internal.queryservices;

import com.spotfinderbackend.analytics.domain.model.queries.*;
import com.spotfinderbackend.analytics.domain.model.readmodels.*;
import com.spotfinderbackend.analytics.domain.services.AnalyticsQueryService;
import com.spotfinderbackend.parkingmonitoring.domain.model.valueobjects.ParkingSlotStatus;
import com.spotfinderbackend.parkingmonitoring.infrastructure.persistence.jpa.repositories.ParkingSlotRepository;
import com.spotfinderbackend.payments.infrastructure.persistence.jpa.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyticsQueryServiceImpl implements AnalyticsQueryService {

    private final ParkingSlotRepository slotRepository;
    private final PaymentRepository paymentRepository;

    public AnalyticsQueryServiceImpl(
            ParkingSlotRepository slotRepository,
            PaymentRepository paymentRepository
    ) {
        this.slotRepository = slotRepository;
        this.paymentRepository = paymentRepository;
    }

    // TS51 → OCCUPANCY
    @Override
    public OccupancyMetrics handle(GetOccupancyMetricsQuery query) {

        var slots = slotRepository.findAll();

        var occupied = slots.stream()
                .filter(s -> s.getStatus() == ParkingSlotStatus.OCCUPIED)
                .count();

        int total = slots.size();

        double rate = total == 0 ? 0 : (double) occupied / total;

        return new OccupancyMetrics(
                total,
                (int) occupied,
                rate
        );
    }

    // TS52 → REVENUE
    @Override
    public RevenueMetrics handle(GetRevenueMetricsQuery query) {

        var payments = paymentRepository.findAll();

        double totalRevenue = payments.stream()
                .mapToDouble(p -> p.getAmount().value().doubleValue())
                .sum();

        int totalTransactions = payments.size();

        return new RevenueMetrics(
                totalRevenue,
                totalTransactions
        );
    }

    // TS53 → HEATMAP
    @Override
    public List<HeatmapPoint> handle(GetHeatmapQuery query) {

        // simplificado (luego se pueden usar sesiones reals)
        var slots = slotRepository.findAll();

        return slots.stream()
                .map(s -> new HeatmapPoint(
                        s.getCode().getValue(),
                        (int) (Math.random() * 10) // mock usage
                ))
                .toList();
    }
}