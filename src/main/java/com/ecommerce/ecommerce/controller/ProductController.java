package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.CreateProductDTO;
import com.ecommerce.ecommerce.dto.ProductResponseDTO;
import com.ecommerce.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 🔓 Public: Read all products
    @GetMapping
    public List<ProductResponseDTO> getAll() {
        return productService.getAllProducts();
    }

    // 🔓 Public: Read single product
    @GetMapping("/{id}")
    public ProductResponseDTO getById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // 🔐 Admin: Create product
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ProductResponseDTO create(@RequestBody @Valid CreateProductDTO dto) {
        return productService.createProduct(dto);
    }

    // 🔐 Admin: Update product
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ProductResponseDTO update(@PathVariable Long id, @RequestBody @Valid CreateProductDTO dto) {
        return productService.updateProduct(id, dto);
    }

    // 🔐 Admin: Delete product
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void delete(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductResponseDTO>> getByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(productService.getByCategory(categoryId));
    }
}

