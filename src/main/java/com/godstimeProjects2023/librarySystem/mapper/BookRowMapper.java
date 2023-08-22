package com.godstimeProjects2023.librarySystem.mapper;

import com.godstimeProjects2023.librarySystem.entity.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setTitle(rs.getString("title"));
        book.setAuthors(rs.getString("authors"));


        // Set other properties here
        return book;
    }
}

