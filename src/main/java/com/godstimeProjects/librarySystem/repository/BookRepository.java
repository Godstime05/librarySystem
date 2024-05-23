package com.godstimeProjects.librarySystem.repository;

import com.godstimeProjects.librarySystem.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    public Book findByTag(String tag);

    public List<Book> findByTitle(String title);
    public List<Book> findByAuthors(String authors);

    @Query(value = "SELECT * from book WHERE status=1", nativeQuery = true)
    public List<Book> findAvailableBooks();

    public List<Book> findByPublisher(String publisher);

    Book getByTag(String tag);
}
