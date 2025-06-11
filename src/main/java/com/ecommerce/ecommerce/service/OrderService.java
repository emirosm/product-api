package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.entity.*;
import com.ecommerce.ecommerce.repository.CartItemRepository;
import com.ecommerce.ecommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartItemRepository cartRepo;
    private final OrderRepository orderRepo;

    public Order placeOrder(User user) {
        List<CartItem> cartItems = cartRepo.findByUser(user);
        if (cartItems.isEmpty()) throw new RuntimeException("Cart is empty");

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);

        for (CartItem item : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProductName(item.getProduct().getName());
            orderItem.setUnitPrice(item.getProduct().getPrice());
            orderItem.setQuantity(item.getQuantity());
            order.getItems().add(orderItem);
        }

        cartRepo.deleteAll(cartItems);
        return orderRepo.save(order);
    }

    public List<Order> getOrders(User user) {
        return orderRepo.findByUser(user);
    }

    public void updateStatus(Long id, OrderStatus status) {
        Order order = orderRepo.findById(id).orElseThrow();
        order.setStatus(status);
        orderRepo.save(order);
    }
}
