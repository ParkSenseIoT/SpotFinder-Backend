package com.spotfinderbackend.analytics.interfaces.rest;

import com.spotfinderbackend.analytics.domain.model.queries.*;
import com.spotfinderbackend.analytics.domain.services.AnalyticsQueryService;
import com.spotfinderbackend.analytics.interfaces.rest.resources.*;
import com.spotfinderbackend.analytics.interfaces.rest.transform.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/analytics")
public class AnalyticsController {

    private final AnalyticsQueryService queryService;

    public AnalyticsController(AnalyticsQueryService queryService) {
        this.queryService = queryService;
    }

    // TS51 → OCCUPANCY
    @GetMapping("/occupancy")
    public ResponseEntity<OccupancyMetricsResource> getOccupancy(
            @RequestParam(required = false) LocalDateTime start,
            @RequestParam(required = false) LocalDateTime end
    ) {

        var metrics = queryService.handle(
                new GetOccupancyMetricsQuery(start, end)
        );

        return ResponseEntity.ok(
                OccupancyMetricsResourceAssembler.toResource(metrics)
        );
    }

    // TS52 → REVENUE
    @GetMapping("/revenue")
    public ResponseEntity<RevenueMetricsResource> getRevenue(
            @RequestParam(required = false) LocalDateTime start,
            @RequestParam(required = false) LocalDateTime end
    ) {

        var metrics = queryService.handle(
                new GetRevenueMetricsQuery(start, end)
        );

        return ResponseEntity.ok(
                RevenueMetricsResourceAssembler.toResource(metrics)
        );
    }

    // TS53 → HEATMAP
    @GetMapping("/heatmap")
    public ResponseEntity<List<HeatmapPointResource>> getHeatmap(
            @RequestParam(required = false) LocalDateTime start,
            @RequestParam(required = false) LocalDateTime end
    ) {

        var points = queryService.handle(
                new GetHeatmapQuery(start, end)
        );

        var response = points.stream()
                .map(HeatmapPointResourceAssembler::toResource)
                .toList();

        return ResponseEntity.ok(response);
    }
}