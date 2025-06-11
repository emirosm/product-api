package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.entity.Payment;
import com.ecommerce.ecommerce.service.PaymentService;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/{orderId}")
    public ResponseEntity<Payment> pay(@PathVariable Long orderId) throws StripeException {
        return ResponseEntity.ok(paymentService.processPayment(orderId));
    }

}
