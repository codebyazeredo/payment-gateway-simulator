package com.api.payment.gateway.enums;

public enum Status {
    CREATED("Criado"),
    APPROVED("Aprovado"),
    DECLINED("Rejeitado"),
    PENDING("Pendente"),
    REFUNDED("Reembolsado"),
    FAILED("Falhou");

    private final String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
