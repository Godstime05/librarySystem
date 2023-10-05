package com.godstimeProjects2023.librarySystem.mapper;

import com.godstimeProjects2023.librarySystem.entity.Role;
import com.godstimeProjects2023.librarySystem.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setActive(rs.getInt("active"));
        user.setRoles(parseRoles(rs.getString("roles"))); // Parse the roles String to List<Role>

        user.setCreatedDate(rs.getDate("created_date"));
        user.setLastModifiedDate(rs.getDate("last_modified_date"));

        return user;
    }


//public class UserRowMapper implements RowMapper<User> {
//    @Override
//    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//        User user = new User();
//        user.setId(rs.getLong("id"));
//        user.setName(rs.getString("name"));
//        user.setUsername(rs.getString("username"));
//        user.setPassword(rs.getString("password"));
//        user.setActive(rs.getInt("active"));
//        user.setRoles(parseRoles(rs.getString("roles"))); // Parse the roles String to List<Role>
//        user.setCreatedDate(rs.getDate("created_date"));
//        user.setLastModifiedDate(rs.getDate("last_modified_date"));
//
//        return user;
//    }



    private List<Role> parseRoles(String rolesString) {
        List<Role> roles = new ArrayList<>();
        String[] roleNames = rolesString.split(","); // Assuming roles are comma-separated
        for (String roleName : roleNames) {
            roles.add(new Role(roleName)); // Create Role objects based on role names
        }
        return roles;
    }
}