package com.api.payment.gateway.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PaymentMethod {
    BOLETO, CREDIT_CARD, PIX;

    @JsonCreator
    public static PaymentMethod fromString(String value) {
        switch (value.toUpperCase()) {

            case "BOLETO":
                return BOLETO;

            case "CREDITCARD":
                return CREDIT_CARD;

            case "PIX":
                return PIX;

            default:
                throw new IllegalArgumentException("Método de pagamento não reconhecido: " + value);
        }
    }
}
