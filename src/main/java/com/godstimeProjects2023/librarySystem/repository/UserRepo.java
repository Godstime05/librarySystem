package com.godstimeProjects2023.librarySystem.repository;

import com.godstimeProjects2023.librarySystem.entity.User;
import com.godstimeProjects2023.librarySystem.mapper.UserRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepo{

    private final JdbcTemplate jdbcTemplate;

    public List<User> findAllByOrderByNameAsc() {
        String sql = "SELECT * FROM user ORDER BY name ASC";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    public User findByUsername(String username) {
        String sql = "SELECT * FROM user WHERE username = ?";
        List<User> users = jdbcTemplate.query(sql, new Object[]{username}, new UserRowMapper());
        return users.isEmpty() ? null : users.get(0);
    }

    public User findByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        List<User> users = jdbcTemplate.query(sql, new Object[]{username, password}, new UserRowMapper());
        return users.isEmpty() ? null : users.get(0);
    }
}

