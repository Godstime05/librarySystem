package com.godstimeProjects2023.librarySystem.mapper;

import com.godstimeProjects2023.librarySystem.entity.Book;
import com.godstimeProjects2023.librarySystem.entity.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setTitle(rs.getString("title"));
        book.setAuthors(rs.getString("authors"));
        book.setPublisher(rs.getString("publisher"));
        book.setTag(rs.getString("tag"));
        book.setIsbn(rs.getString("isbn"));
        book.setStatus(rs.getInt("publisher"));

         // Map the Category object using categoryId column from the database
        Category category = new Category();
        category.setId(rs.getLong("category_id"));
        //  category.setName(rs.getString("category_name"));
        book.setCategory(category);

        // Set the current date and time for the date field
        Timestamp timestamp = rs.getTimestamp("created_Date");
        if (timestamp != null) {
            book.setCreatedDate(timestamp.toLocalDateTime());
        }
        return book;
    }
}

