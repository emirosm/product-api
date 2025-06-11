package com.ecommerce.ecommerce.service;


import com.ecommerce.ecommerce.entity.Order;
import com.ecommerce.ecommerce.entity.OrderStatus;
import com.ecommerce.ecommerce.entity.Payment;
import com.ecommerce.ecommerce.repository.OrderRepository;
import com.ecommerce.ecommerce.repository.PaymentRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.stripe.Stripe.apiKey;


@Service
@RequiredArgsConstructor
public class PaymentService {
    @Value("${stripe.api.key}")
    private String stripeApiKey;

    private final PaymentRepository paymentRepo;
    private final OrderRepository orderRepo;

    public Payment processPayment(Long orderId) throws StripeException {
        apiKey = stripeApiKey;

        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        double amount = order.getItems().stream()
                .mapToDouble(item -> item.getUnitPrice() * item.getQuantity())
                .sum();

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount((long)(amount * 100))
                .setCurrency("usd")
                .build();

        PaymentIntent intent = PaymentIntent.create(params);

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(amount);
        payment.setStatus("PAID"); // mock it
        payment.setStripePaymentId(intent.getId());

        order.setStatus(OrderStatus.PAID);
        orderRepo.save(order);

        return paymentRepo.save(payment);
    }
}


