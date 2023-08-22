package com.godstimeProjects2023.librarySystem.repository;

import com.godstimeProjects2023.librarySystem.entity.Category;
import com.godstimeProjects2023.librarySystem.mapper.CategoryRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryRepo {

    private final JdbcTemplate jdbcTemplate;

    public List<Category> findAllByOrderByNameAsc() {
        String sql = "SELECT * FROM category ORDER BY name ASC";
        return jdbcTemplate.query(sql, new CategoryRowMapper());
    }

    public Optional<Category> findByName(String name) {
        String sql = "SELECT * FROM category WHERE name = ?";
        List<Category> categories = jdbcTemplate.query(sql, new Object[]{name}, new CategoryRowMapper());
        return categories.isEmpty() ? Optional.empty() : Optional.of(categories.get(0));
    }
}

