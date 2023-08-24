package com.godstimeProjects2023.librarySystem.mapper;

import com.godstimeProjects2023.librarySystem.entity.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class CategoryRowMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category = new Category();
        category.setId(rs.getLong("id"));
        category.setName(rs.getString("name"));
        category.setShortName(rs.getString("short_name"));
        category.setNotes(rs.getString("notes"));

        // Map the createdDate column to a Date field
        Timestamp timestamp = rs.getTimestamp("created_date");
        if (timestamp != null) {
            category.setCreatedDate(new Date(timestamp.getTime()));
        }

        return category;
    }
}

