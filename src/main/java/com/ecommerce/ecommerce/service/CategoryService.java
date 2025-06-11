package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.CategoryResponseDTO;
import com.ecommerce.ecommerce.dto.CreateCategoryDTO;
import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryResponseDTO create(CreateCategoryDTO dto) {
        if (categoryRepository.findByName(dto.getName()).isPresent()) {
            throw new RuntimeException("Category already exists");
        }
        Category category = new Category();
        category.setName(dto.getName());
        category = categoryRepository.save(category);
        return new CategoryResponseDTO(category.getId(), category.getName());
    }

    public List<CategoryResponseDTO> getAll() {
        return categoryRepository.findAll().stream()
                .map(cat -> new CategoryResponseDTO(cat.getId(), cat.getName()))
                .toList();
    }

    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found");
        }
        categoryRepository.deleteById(id);
    }

    public CategoryResponseDTO update(Long id, CreateCategoryDTO dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(dto.getName());
        category = categoryRepository.save(category);
        return new CategoryResponseDTO(category.getId(), category.getName());
    }
}
