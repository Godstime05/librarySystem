package com.godstimeProjects2023.librarySystem.repository;

import com.godstimeProjects2023.librarySystem.entity.Book;
import com.godstimeProjects2023.librarySystem.entity.Category;
import com.godstimeProjects2023.librarySystem.mapper.BookRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class BookRepo {

    private final JdbcTemplate jdbcTemplate;

//    public Book findByTag(String tag) {
//        String sql = "SELECT * FROM book WHERE tag = ?";
//        return jdbcTemplate.queryForObject(sql, new Object[]{tag}, new BookRowMapper());
//    }
//
//    public List<Book> findByCategory(Category category) {
//        String sql = "SELECT * FROM book WHERE category = ?";
//        return jdbcTemplate.query(sql, new Object[]{category.toString()}, new BookRowMapper());
//    }
//
//    public List<Book> findByTitle(String title) {
//        String sql = "SELECT * FROM book WHERE title = ?";
//        return jdbcTemplate.query(sql, new Object[]{title}, new BookRowMapper());
//    }
//
//    public List<Book> findByAuthors(String authors) {
//        String sql = "SELECT * FROM book WHERE authors = ?";
//        return jdbcTemplate.query(sql, new Object[]{authors}, new BookRowMapper());
//    }
//
//    public List<Book> findAvailableBooks() {
//        String sql = "SELECT * FROM book WHERE status = 1";
//        return jdbcTemplate.query(sql, new BookRowMapper());
//    }
//
//    public List<Book> findByCategoryAndStatus(Category category, Integer bookStatusAvailable) {
//        String sql = "SELECT * FROM book WHERE category = ? AND status = ?";
//        return jdbcTemplate.query(sql, new Object[]{category.toString(), bookStatusAvailable}, new BookRowMapper());
//    }
//
//    public List<Book> findByPublisher(String publisher) {
//        String sql = "SELECT * FROM book WHERE publisher = ?";
//        return jdbcTemplate.query(sql, new Object[]{publisher}, new BookRowMapper());
//    }


    ////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\////////////////////////////////////////////////////////////////////||||||

    public Book getByTag(String tag) {
        String sql = "SELECT * FROM book WHERE tag = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{tag}, new BookRowMapper());
    }

    public Book addNewBook(Book book) {
        // Implement the logic to insert a new book
        String sql = "INSERT INTO book (title, authors, category_id, publisher, status) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, book.getTitle(), book.getAuthors(), book.getCategory().getId(), book.getPublisher(), book.getStatus());
        return book;
    }
    public Book addNewBooks(Book book) {
        String insertSql = "INSERT INTO book(title, authors, category, status, tag) VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthors());
            ps.setString(3, String.valueOf(book.getCategory()));
            ps.setString(2, book.getPublisher());
            ps.setString(2, String.valueOf(book.getStatus()));
            ps.setString(2, book.getTag());

            return ps;
        }, keyHolder);
        book.setId((Long) keyHolder.getKeys().get("id"));
        return book;
    }

    public Book saveBook(Book book) {
        // Implement the logic to update a book
        String sql = "UPDATE book SET title = ?, authors = ?, category_id = ?, publisher = ?, status = ?, tag=? WHERE id = ?";
        jdbcTemplate.update(sql, book.getTitle(), book.getAuthors(), book.getCategory().getId(), book.getPublisher(), book.getStatus(), book.getId());
        return book;
    }

    public Book getBook(Long id) {
        String sql = "SELECT * FROM book WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BookRowMapper());
    }
    public Book getBook1(Long id) {
        return getBookById(id).orElse(null);
    }

    public Optional<Book> getBookById(Long id) {
        String sql = "SELECT * FROM book WHERE id = ?";
        List<Book> books = jdbcTemplate.query(sql, new Object[]{id}, new BookRowMapper());
        return books.isEmpty() ? Optional.empty() : Optional.of(books.get(0));
    }

    public List<Book> getAll() {
        String sql = "SELECT * FROM book";
        return jdbcTemplate.query(sql, new BookRowMapper());
    }

    public Long getTotalCount() {
        String sql = "SELECT COUNT(*) FROM book";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    public List<Book> getByAuthorName(String authors) {
        String sql = "SELECT * FROM book WHERE authors = ?";
        return jdbcTemplate.query(sql, new Object[]{authors}, new BookRowMapper());
    }

//    public List<Book> getBooksByIdList0(List<Long> ids) {
//        String sql = "SELECT * FROM book WHERE id IN (:ids)";
//        MapSqlParameterSource parameters = new MapSqlParameterSource("ids", ids);
//        return jdbcTemplate.query(sql, parameters, new BookRowMapper());
//    }

    public List<Book> getBooksByIdList1(List<Long> ids) {
        String sql = "SELECT * FROM book WHERE id IN (?)";
        String idList = ids.stream().map(Object::toString).collect(Collectors.joining(", "));
        return jdbcTemplate.query(sql, new Object[]{idList}, new BookRowMapper());
    }

    public List<Book> getBooksByIdList(List<Long> ids) {
        String sql = "SELECT * FROM book WHERE id IN (?)";
        String commaSeparatedIds = StringUtils.collectionToCommaDelimitedString(ids);
        return jdbcTemplate.query(sql, new Object[]{commaSeparatedIds}, new BookRowMapper());
    }

    public List<Book> getByCategory(Category category) {
        String sql = "SELECT * FROM book WHERE category = ?";
        return jdbcTemplate.query(sql, new Object[]{category.toString()}, new BookRowMapper());
    }

    public List<Book> getBookWithTitle(String title) {
        String sql = "SELECT * FROM book WHERE title = ?";
        return jdbcTemplate.query(sql, new Object[]{title}, new BookRowMapper());
    }

    public List<Book> getAvailableBooks() {
        String sql = "SELECT * FROM book WHERE status = 1";
        return jdbcTemplate.query(sql, new BookRowMapper());
    }

    public List<Book> getAvailableByCategory(Category category) {
        String sql = "SELECT * FROM book WHERE category = ? AND status = 1";
        return jdbcTemplate.query(sql, new Object[]{category.toString()}, new BookRowMapper());
    }

    public List<Book> getByPublisherName(String publisher) {
        String sql = "SELECT * FROM book WHERE publisher = ?";
        return jdbcTemplate.query(sql, new Object[]{publisher}, new BookRowMapper());
    }


}

