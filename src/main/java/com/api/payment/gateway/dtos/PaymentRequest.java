package com.api.payment.gateway.dtos;

import com.api.payment.gateway.models.Transaction;
import jakarta.validation.constraints.*;

public record PaymentRequest(
        @NotNull @Positive
        Double amount,

        @NotBlank
        String currency,

        @NotNull
        Transaction.PaymentMethod paymentMethod,

        @Size(min = 13, max = 19)
        String cardNumber,

        @Size(min = 2, max = 5)
        String cardExpiry,

        @Size(min = 3, max = 4)
        String cardCvv,

        String pixKey,
        String description
) {}