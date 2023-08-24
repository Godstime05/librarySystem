package com.godstimeProjects2023.librarySystem.service;

import com.godstimeProjects2023.librarySystem.entity.Category;
import com.godstimeProjects2023.librarySystem.repository.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepo categoryRepository;

    public Category addNewCategory(Category category) {
        return categoryRepository.addNew(category);
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.getCategory(id);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.getAll();
    }

    public Long getTotalCategoryCount() {
        return categoryRepository.getTotalCount();
    }

    public List<Category> getAllCategoriesBySort() {
        return categoryRepository.getAllBySort();
    }

    public Optional<Category> getCategoryByName(String name) {
        return categoryRepository.getCategory(name);
    }

    public boolean hasUsage(Category category) {
        return categoryRepository.hasUsage(category);
    }

    public void deleteCategoryById(Long id) {
        categoryRepository.deleteCategory(id);
    }

    public void deleteCategoryByCategoryObject(Category category) {
        categoryRepository.deleteCategoryByCategoryObject(category);
    }
}
