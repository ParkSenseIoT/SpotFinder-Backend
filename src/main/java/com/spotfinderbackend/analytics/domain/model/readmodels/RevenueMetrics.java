package com.spotfinderbackend.analytics.domain.model.readmodels;

public record RevenueMetrics(
        double totalRevenue,
        int totalTransactions
) {}