package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.CartItemDTO;
import com.ecommerce.ecommerce.dto.CartItemResponseDTO;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/add")
    public ResponseEntity<Void> addToCart(@RequestBody CartItemDTO dto,
                                          @AuthenticationPrincipal User user) {
        cartService.addToCart(user, dto.getProductId(), dto.getQuantity());
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/remove")
    public ResponseEntity<Void> removeFromCart(@RequestParam Long productId,
                                               @AuthenticationPrincipal User user) {
        cartService.removeFromCart(user, productId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<CartItemResponseDTO>> getCart(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(cartService.getCart(user));
    }
}