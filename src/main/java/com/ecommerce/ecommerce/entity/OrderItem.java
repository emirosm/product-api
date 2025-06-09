package com.ecommerce.ecommerce.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;
    private double priceAtPurchase;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Order order;
}