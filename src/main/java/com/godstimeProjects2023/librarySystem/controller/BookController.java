package com.godstimeProjects2023.librarySystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.godstimeProjects2023.librarySystem.constants.Constants;
import com.godstimeProjects2023.librarySystem.entity.Book;
import com.godstimeProjects2023.librarySystem.entity.Category;
import com.godstimeProjects2023.librarySystem.service.BookService;
import com.godstimeProjects2023.librarySystem.service.CategoryService;
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
    private final CategoryService categoryService;

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
        Book newBook = bookService.addNewBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updatedBook = bookService.saveBook(book);
        return ResponseEntity.ok(updatedBook);
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

    @GetMapping("/count")
    public ResponseEntity<String> getTotalBookCount() {
        Long count = bookService.getTotalBookCount();

        if (count != 0){
            return new ResponseEntity<String>(count.toString(), HttpStatus.OK);
        }
        return Constants.getResponse(Constants.INCOMPLETE_DETAILS, HttpStatus.NO_CONTENT);
    }


    @GetMapping("/by-author/{authors}")
    public ResponseEntity<List<Book>> getBooksByAuthor0(@PathVariable String authors) {
        List<Book> books = bookService.getBooksByAuthorName(authors);
        return ResponseEntity.ok(books);
    }
    @GetMapping("/getByAuthor/{authors}")
    public ResponseEntity<String> getBooksByAuthor(@PathVariable("authors") String authors) {
        List<Book> books = bookService.getBooksByAuthorName(authors);
        try {
            if (books != null){
                String bookX = obj.writeValueAsString(books);
                return new ResponseEntity<String>(bookX, HttpStatus.OK);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/by-ids")
    public ResponseEntity<List<Book>> getBooksByIds0(@RequestParam List<Long> ids) {
        List<Book> books = bookService.getBooksByIdList(ids);
        return ResponseEntity.ok(books);
    }
    @GetMapping("/getByIds/{ids}")
    public ResponseEntity<String> getBooksByIds(@PathVariable List<Long> ids) {
        List<Book> books = bookService.getBooksByIdList(ids);
        try {
            String bookX = obj.writeValueAsString(books);
            return new ResponseEntity<String>(bookX, HttpStatus.FOUND);
        } catch (JsonProcessingException e) {

            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/by-category/{category}")
    public ResponseEntity<List<Book>> getBooksByCategory(@PathVariable Category category) {
        List<Book> books = bookService.getBooksByCategory(category);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/by-title/{title}")
    public ResponseEntity<List<Book>> getBooksByTitle(@PathVariable String title) {
        List<Book> books = bookService.getBooksByTitle(title);
        try {
            if (books != null){
                return new ResponseEntity<List<Book>>(books, HttpStatus.FOUND);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<List<Book>> getAvailableBooks0() {
        List<Book> books = bookService.getAvailableBooks();
        return ResponseEntity.ok(books);
    }
    @GetMapping("/availableBooks")
    public ResponseEntity<String> getAvailableBooks() {
        List<Book> books = bookService.getAvailableBooks();
        try {
            if (books !=null){
                String bookX = obj.writeValueAsString(books);
                return new ResponseEntity<String>(bookX, HttpStatus.OK);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<String>("No books are available", HttpStatus.OK);
    }

    @GetMapping("/available-by-category/{category}")
    public ResponseEntity<List<Book>> getAvailableBooksByCategory0(@PathVariable Category category) {
        List<Book> books = bookService.getAvailableBooksByCategory(category);
        return ResponseEntity.ok(books);
    }
    @GetMapping("/availableByCategory/{category}")
    public ResponseEntity<String> getAvailableBooksByCategory(@PathVariable String name) {

        Optional <Category>category = categoryService.getCategoryByName(name);
        try {
            if (category.isPresent()){
                List<Book> books = bookService.getAvailableBooksByCategory(category.get());

                String bookX = obj.writeValueAsString(books);
                return new ResponseEntity<String>(bookX, HttpStatus.FOUND);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new  ResponseEntity<String>("No Books Are Available", HttpStatus.OK);
    }

    @GetMapping("/by-publisher/{publisher}")
    public ResponseEntity<List<Book>> getBooksByPublisher0(@PathVariable String publisher) {
        List<Book> books = bookService.getBooksByPublisherName(publisher);
        return ResponseEntity.ok(books);
    }
    @GetMapping("/getByPublisher/{publisher}")
    public ResponseEntity<String> getBooksByPublisher(@PathVariable("publisher") String publisher) {
        List<Book> books = bookService.getBooksByPublisherName(publisher);
        try {
            if (books !=null){
                String bookX = obj.writeValueAsString(books);
                return new ResponseEntity<String>(bookX, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
