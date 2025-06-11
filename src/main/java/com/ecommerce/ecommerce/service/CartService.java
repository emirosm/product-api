package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.CartItemResponseDTO;
import com.ecommerce.ecommerce.entity.CartItem;
import com.ecommerce.ecommerce.entity.Product;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.repository.CartItemRepository;
import com.ecommerce.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CartService {
    private final CartItemRepository cartRepo;
    private final ProductRepository productRepo;

    public void addToCart(User user, Long productId, int quantity) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem item = cartRepo.findByUserAndProduct(user, product)
                .orElse(new CartItem());

        item.setUser(user);
        item.setProduct(product);
        item.setQuantity(item.getQuantity() + quantity);

        cartRepo.save(item);
    }

    public void removeFromCart(User user, Long productId) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        cartRepo.deleteByUserAndProduct(user, product);
    }

    public List<CartItemResponseDTO> getCart(User user) {
        return cartRepo.findByUser(user).stream()
                .map(item -> CartItemResponseDTO.builder()
                        .productId(item.getProduct().getId())
                        .productName(item.getProduct().getName())
                        .unitPrice(item.getProduct().getPrice())
                        .quantity(item.getQuantity())
                        .totalPrice(item.getProduct().getPrice() * item.getQuantity())
                        .build())
                .toList();
    }
}
