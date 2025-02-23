package com.api.payment.gateway.dtos;

import java.time.Instant;

public record TransactionResponse(
        String transactionId,
        String status,
        Double amount,
        String currency,
        String paymentMethod,
        String description,
        Instant createdAt
) {}