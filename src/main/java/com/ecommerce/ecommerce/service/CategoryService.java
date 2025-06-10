package com.ecommerce.ecommerce.service;

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

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    public Category update(Long id, Category updated) {
        Category category = getById(id);
        category.setName(updated.getName());
        return categoryRepository.save(category);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
