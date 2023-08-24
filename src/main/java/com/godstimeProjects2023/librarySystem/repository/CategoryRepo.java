package com.godstimeProjects2023.librarySystem.repository;

import com.godstimeProjects2023.librarySystem.entity.Category;
import com.godstimeProjects2023.librarySystem.mapper.CategoryRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    //////////////////////////////////////////

        public Category addNew(Category category) {
            String insertSql = "INSERT INTO category(name, short_name, notes, created_date) VALUES (?,?,?,?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, category.getName());
                ps.setString(2, category.getShortName());
                ps.setString(3, category.getNotes());
                ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
                ps.setString(1, category.getName());

                return ps;
            }, keyHolder);
            category.setId((Long) keyHolder.getKeys().get("id"));
            return category;
        }

        public Category save(Category category) {
            String updateSql = "UPDATE category SET name = ?, short_name=?, notes=? WHERE id = ?";
            jdbcTemplate.update(updateSql, category.getName(), category.getShortName(), category.getNotes(), category.getId());
            return category;
        }

        public Optional<Category> getCategory(Long id) {
            String sql = "SELECT * FROM category WHERE id = ?";
            List<Category> categories = jdbcTemplate.query(sql, new Object[]{id}, new CategoryRowMapper());
            return categories.isEmpty() ? Optional.empty() : Optional.of(categories.get(0));
        }

        public List<Category> getAll() {
            String sql = "SELECT * FROM category";
            return jdbcTemplate.query(sql, new CategoryRowMapper());
        }

        public Long getTotalCount() {
            String sql = "SELECT COUNT(*) FROM category";
            return jdbcTemplate.queryForObject(sql, Long.class);
        }

        public List<Category> getAllBySort() {
            String sql = "SELECT * FROM category ORDER BY name ASC";
            return jdbcTemplate.query(sql, new CategoryRowMapper());
        }

        public Optional<Category> getCategory(String name) {
            String sql = "SELECT * FROM category WHERE name = ?";
            List<Category> categories = jdbcTemplate.query(sql, new Object[]{name}, new CategoryRowMapper());
            return categories.isEmpty() ? Optional.empty() : Optional.of(categories.get(0));
        }

//        public boolean hasUsage(Category category) {
//            // Implement logic to check if the category has any usage (e.g., associated books)
//            return false; // Replace with actual implementation
//        }

    public boolean hasUsage(Category category) {
        String sql = "SELECT COUNT(*) FROM book WHERE category_id = ?";
        Long count = jdbcTemplate.queryForObject(sql, Long.class, category.getId());
        return count != null && count > 0;
    }
//    public boolean hasUsage(Category category) {
//        String sql = "SELECT COUNT(*) FROM book WHERE category_id = ?";
//        Long count = jdbcTemplate.queryForObject(sql, Long.class, category.getId());
//        return count > 0;
//    }


    public void deleteCategory(Long id) {
            String deleteSql = "DELETE FROM category WHERE id = ?";
            jdbcTemplate.update(deleteSql, id);
        }

        public void deleteCategoryByCategoryObject(Category category) {
            deleteCategory(category.getId());
        }


}

