package com.godstimeProjects2023.librarySystem.mapper;

import com.godstimeProjects2023.librarySystem.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));

        // TO DO


        // Set other properties here
        return user;
    }
}

