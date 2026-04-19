package com.spotfinderbackend.analytics.interfaces.rest.resources;

public record RevenueMetricsResource(
        double totalRevenue,
        int totalTransactions
) {}