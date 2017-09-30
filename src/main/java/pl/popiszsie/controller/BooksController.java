package pl.popiszsie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.popiszsie.model.Book;
import pl.popiszsie.service.BooksService;
import pl.popiszsie.util.BookSaveFailedException;
import pl.popiszsie.util.BookValidator;

import java.util.List;

@RestController
public class BooksController {

    @Autowired
    BooksService booksService;

    @GetMapping("/books/book")
    public ResponseEntity getAllBooks() {
        return new ResponseEntity<List<Book>>(booksService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/books/book/{id}")
    public ResponseEntity getBookById(@PathVariable("id") Long id) {
        return new ResponseEntity<Book>(booksService.getBookById(id), HttpStatus.OK);
    }

    @PostMapping("/books/book")
    public ResponseEntity sendBooks(String bookText) {
        if(!BookValidator.validate(bookText)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        try{
            booksService.putBook(bookText);
        } catch(BookSaveFailedException ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
