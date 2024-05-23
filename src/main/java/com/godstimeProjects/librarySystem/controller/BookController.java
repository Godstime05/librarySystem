package com.godstimeProjects.librarySystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.godstimeProjects.librarySystem.constants.Constants;
import com.godstimeProjects.librarySystem.entity.Book;
import com.godstimeProjects.librarySystem.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    ObjectMapper obj = new ObjectMapper();

    @GetMapping("/{tag}")
    public ResponseEntity<Book> getBookByTag(@PathVariable String tag) {
        Book book = bookService.getByTag(tag);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Book> addNewBook(@RequestBody Book book) {
        Book newBook = bookService.saveBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book existingBook = bookService.getBookById(id).orElse(null);
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthors(book.getAuthors());
        existingBook.setIsbn(book.getIsbn());
        return bookService.saveBook(existingBook);
    }

    @GetMapping("/{id0}")
    public ResponseEntity<Book> getBookById0(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/{id}")
    public ResponseEntity<String> getBookById(@PathVariable Long id){

        Optional<Book> optional = bookService.getBookById(id);

        try {
            if (optional.isPresent()){
                Book book = optional.get();
                String book1 = obj.writeValueAsString(book);
                return new ResponseEntity<>(book1, HttpStatus.FOUND);
            }else {
                return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Constants.getResponse(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/getAll0")
    public ResponseEntity<List<Book>> getAllBooks0() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        try {
            if (books != null){
                return new ResponseEntity<List<Book>>(books, HttpStatus.FOUND);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public void deleteBook (@PathVariable Long id){
        bookService.deleteBook(id);
    }

}
