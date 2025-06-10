package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // 🔓 All users: View categories
    @GetMapping
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    // 🔐 Admin only: Create category
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Category create(@RequestBody @Valid Category category) {
        return categoryService.create(category);
    }

    // 🔐 Admin only: Update category
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Category update(@PathVariable Long id, @RequestBody @Valid Category category) {
        return categoryService.update(id, category);
    }

    // 🔐 Admin only: Delete category
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
