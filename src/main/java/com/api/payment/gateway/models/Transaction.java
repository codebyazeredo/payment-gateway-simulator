package com.api.payment.gateway.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.Instant;

@Entity
public class Transaction {

    public enum PaymentMethod {
        CREDIT_CARD,
        PIX,
        BOLETO
    }

    public enum Status {
        CREATED,
        APPROVED,
        DECLINED,
        PENDING,
        REFUNDED,
        FAILED
    }

    @Id
    private String transactionId;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    @Positive
    @NotNull
    private Double amount;

    @NotBlank
    private String currency;

    @Size(min = 13, max = 19)
    private String cardNumber;

    @Size(min = 2, max = 5)
    private String cardExpiry;

    @Size(min = 3, max = 4)
    private String cardCvv;

    private String description;
    private String pixKey;

    @Enumerated(EnumType.STRING)
    @NotNull
    private PaymentMethod paymentMethod;

    private Instant createdAt;
    private Instant updatedAt;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public @NotNull Status getStatus() {
        return status;
    }

    public void setStatus(@NotNull Status status) {
        this.status = status;
    }

    public @Positive @NotNull Double getAmount() {
        return amount;
    }

    public void setAmount(@Positive @NotNull Double amount) {
        this.amount = amount;
    }

    public @NotBlank String getCurrency() {
        return currency;
    }

    public void setCurrency(@NotBlank String currency) {
        this.currency = currency;
    }

    public @Size(min = 13, max = 19) String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(@Size(min = 13, max = 19) String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public @Size(min = 2, max = 5) String getCardExpiry() {
        return cardExpiry;
    }

    public void setCardExpiry(@Size(min = 2, max = 5) String cardExpiry) {
        this.cardExpiry = cardExpiry;
    }

    public @Size(min = 3, max = 4) String getCardCvv() {
        return cardCvv;
    }

    public void setCardCvv(@Size(min = 3, max = 4) String cardCvv) {
        this.cardCvv = cardCvv;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPixKey() {
        return pixKey;
    }

    public void setPixKey(String pixKey) {
        this.pixKey = pixKey;
    }

    public @NotNull PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(@NotNull PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
