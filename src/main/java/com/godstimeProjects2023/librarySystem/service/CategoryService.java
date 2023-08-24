package com.godstimeProjects2023.librarySystem.service;

import com.godstimeProjects2023.librarySystem.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    public Optional<Category> getCategory(String name) {
        return null;
    }
}
