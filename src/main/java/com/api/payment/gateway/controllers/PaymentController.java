package com.api.payment.gateway.controllers;

import com.api.payment.gateway.dtos.ApiResponse;
import com.api.payment.gateway.dtos.PaymentRequest;
import com.api.payment.gateway.dtos.TransactionResponse;
import com.api.payment.gateway.services.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TransactionResponse>> createPayment(
            @Valid @RequestBody PaymentRequest request) {

        TransactionResponse response = service.processPayment(request);

        return ResponseEntity
                .created(URI.create("/api/payments/" + response.transactionId()))
                .body(ApiResponse.success(response));
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<ApiResponse<TransactionResponse>> getStatus(
            @PathVariable String transactionId) {

        TransactionResponse transaction = service.getTransaction(transactionId);

        if (transaction == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.<TransactionResponse>error("Transação não encontrada"));
        }

        return ResponseEntity.ok(ApiResponse.success(transaction));
    }
}
