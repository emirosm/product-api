package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.*;
import com.ecommerce.ecommerce.entity.*;
import com.ecommerce.ecommerce.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductResponseDTO createProduct(CreateProductDTO dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .category(category)
                .build();

        productRepository.save(product);

        return mapToDto(product);
    }

    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return mapToDto(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public ProductResponseDTO updateProduct(Long id, CreateProductDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setCategory(category);

        productRepository.save(product);
        return mapToDto(product);
    }

    private ProductResponseDTO mapToDto(Product p) {
        return ProductResponseDTO.builder()
                .id(p.getId())
                .name(p.getName())
                .description(p.getDescription())
                .price(p.getPrice())
                .categoryName(p.getCategory().getName())
                .build();
    }
}
