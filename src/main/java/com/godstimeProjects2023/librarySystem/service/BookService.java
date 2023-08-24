package com.godstimeProjects2023.librarySystem.service;

import com.godstimeProjects2023.librarySystem.constants.Constants;
import com.godstimeProjects2023.librarySystem.entity.Book;
import com.godstimeProjects2023.librarySystem.entity.Category;
import com.godstimeProjects2023.librarySystem.repository.BookRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepo bookRepository;

    public Book getByTag(String tag) {
        return bookRepository.getByTag(tag);
    }

    public Book addNewBook(@Valid Book book) {
        book.setCreatedDate(new Date());
        book.setStatus( Constants.BOOK_STATUS_AVAILABLE );
        return bookRepository.addNewBook(book);
    }

    public Book saveBook(Book book) {
        return bookRepository.saveBook(book);
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.getBookById(id);
    }

    public List<Book> getAllBooks() {
        return bookRepository.getAll();
    }

    public Long getTotalBookCount() {
        return bookRepository.getTotalCount();
    }

    public List<Book> getBooksByAuthorName(String authors) {
        return bookRepository.getByAuthorName(authors);
    }

    public List<Book> getBooksByIdList(List<Long> ids) {
        return bookRepository.getBooksByIdList(ids);
    }

    public List<Book> getBooksByCategory(Category category) {
        return bookRepository.getByCategory(category);
    }

    public List<Book> getBooksByTitle(String title) {
        return bookRepository.getBookWithTitle(title);
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.getAvailableBooks();
    }

    public List<Book> getAvailableBooksByCategory(Category category) {
        return bookRepository.getAvailableByCategory(category);
    }

    public List<Book> getBooksByPublisherName(String publisher) {
        return bookRepository.getByPublisherName(publisher);
    }
}

