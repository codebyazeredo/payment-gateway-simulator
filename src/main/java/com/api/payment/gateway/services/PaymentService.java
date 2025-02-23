package com.api.payment.gateway.services;

import com.api.payment.gateway.dtos.PaymentRequest;
import com.api.payment.gateway.dtos.TransactionResponse;
import com.api.payment.gateway.models.Transaction;
import com.api.payment.gateway.repositories.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class PaymentService {

    private final TransactionRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    public PaymentService(TransactionRepository repository) {
        this.repository = repository;
    }

    public TransactionResponse processPayment(PaymentRequest request) {
        validatePaymentRequest(request);

        Transaction transaction = new Transaction();
        transaction.setTransactionId(generateTransactionId(request.paymentMethod()));
        transaction.setStatus(Transaction.Status.CREATED);
        transaction.setAmount(request.amount());
        transaction.setCurrency(request.currency());
        transaction.setPaymentMethod(request.paymentMethod());
        transaction.setCreatedAt(Instant.now());
        transaction.setUpdatedAt(Instant.now());
        transaction.setCardNumber(request.cardNumber());
        transaction.setCardExpiry(request.cardExpiry());
        transaction.setCardCvv(request.cardCvv());
        transaction.setDescription(request.description());
        transaction.setPixKey(request.pixKey());

        repository.save(transaction);
        logger.info("Pagamento processado: {}", transaction.getTransactionId());

        return new TransactionResponse(
                transaction.getTransactionId(),
                transaction.getStatus().name(),
                transaction.getAmount(),
                transaction.getCurrency(),
                transaction.getPaymentMethod().name(),
                transaction.getDescription(),
                transaction.getCreatedAt()
        );
    }

    private void validatePaymentRequest(PaymentRequest request) {
        if (request.paymentMethod() == Transaction.PaymentMethod.CREDIT_CARD) {
            if (request.cardNumber() == null || request.cardExpiry() == null || request.cardCvv() == null) {
                throw new IllegalArgumentException("Para pagamentos com cartão de crédito são necessárias todas as informações do cartão");
            }
        }
    }

    private String generateTransactionId(Transaction.PaymentMethod method) {
        return "%s-%s".formatted(method.name(), UUID.randomUUID());
    }

    public TransactionResponse getTransaction(String transactionId) {
        Transaction transaction = repository.findByTransactionId(transactionId)
                .orElseThrow(() -> new IllegalArgumentException("Transação não encontrada"));

        return new TransactionResponse(
                transaction.getTransactionId(),
                transaction.getStatus().name(),
                transaction.getAmount(),
                transaction.getCurrency(),
                transaction.getPaymentMethod().name(),
                transaction.getDescription(),
                transaction.getCreatedAt()
        );
    }
}
