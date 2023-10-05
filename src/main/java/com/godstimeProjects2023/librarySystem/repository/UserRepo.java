package com.godstimeProjects2023.librarySystem.repository;

import com.godstimeProjects2023.librarySystem.entity.User;
import com.godstimeProjects2023.librarySystem.mapper.UserRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
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

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    public User addNew(User user) {
        String insertSql = "INSERT INTO user(name, username, password, active, role, created_date, last_modified_date) VALUES (?,?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getActive());
            ps.setString(5, user.getRoles().toString());
            ps.setDate(6, new java.sql.Date(user.getCreatedDate().getTime())); // Assuming createdDate is of type java.util.Date
            ps.setDate(7, new java.sql.Date(user.getLastModifiedDate().getTime())); // Assuming lastModifiedDate is of type java.util.Date

            return ps;
        }, keyHolder);
        user.setId((Long) keyHolder.getKeys().get("id"));
        return user;
    }

    public User getUserValidate(String username, String password) {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        List<User> users = jdbcTemplate.query(sql, new Object[]{username, password}, new UserRowMapper());
        return users.isEmpty() ? null : users.get(0);
    }

    public User getByUsername(String username) {
        String sql = "SELECT * FROM user WHERE username = ?";
        List<User> users = jdbcTemplate.query(sql, new Object[]{username}, new UserRowMapper());
        return users.isEmpty() ? null : users.get(0);
    }

    public User registerUser(User user) {
        return addNew(user);
    }
}
