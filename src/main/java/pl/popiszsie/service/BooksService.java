package pl.popiszsie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.popiszsie.dao.BooksRepository;
import pl.popiszsie.model.Book;
import pl.popiszsie.util.BookSaveFailedException;

import java.util.List;

@Service
public class BooksService {

    @Autowired
    private BooksRepository booksRepository;

    public List<Book> getAllBooks() {
        return booksRepository.findAll();
    }


    public void putBook(String bookText) throws BookSaveFailedException {
        booksRepository.save(Book.builder()
                .text(bookText)
                .build());
    }

    public Book getBookById(Long id) {
        return booksRepository.findOne(id);
    }
}
