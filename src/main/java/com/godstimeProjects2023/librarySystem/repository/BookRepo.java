package com.godstimeProjects2023.librarySystem.repository;

import com.godstimeProjects2023.librarySystem.entity.Book;
import com.godstimeProjects2023.librarySystem.entity.Category;
import com.godstimeProjects2023.librarySystem.mapper.BookRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BookRepo {

    private final JdbcTemplate jdbcTemplate;

    public Book findByTag(String tag) {
        String sql = "SELECT * FROM book WHERE tag = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{tag}, new BookRowMapper());
    }

    public List<Book> findByCategory(Category category) {
        String sql = "SELECT * FROM book WHERE category = ?";
        return jdbcTemplate.query(sql, new Object[]{category.toString()}, new BookRowMapper());
    }

    public List<Book> findByTitle(String title) {
        String sql = "SELECT * FROM book WHERE title = ?";
        return jdbcTemplate.query(sql, new Object[]{title}, new BookRowMapper());
    }

    public List<Book> findByAuthors(String authors) {
        String sql = "SELECT * FROM book WHERE authors = ?";
        return jdbcTemplate.query(sql, new Object[]{authors}, new BookRowMapper());
    }

    public List<Book> findAvailableBooks() {
        String sql = "SELECT * FROM book WHERE status = 1";
        return jdbcTemplate.query(sql, new BookRowMapper());
    }

    public List<Book> findByCategoryAndStatus(Category category, Integer bookStatusAvailable) {
        String sql = "SELECT * FROM book WHERE category = ? AND status = ?";
        return jdbcTemplate.query(sql, new Object[]{category.toString(), bookStatusAvailable}, new BookRowMapper());
    }

    public List<Book> findByPublisher(String publisher) {
        String sql = "SELECT * FROM book WHERE publisher = ?";
        return jdbcTemplate.query(sql, new Object[]{publisher}, new BookRowMapper());
    }
}

