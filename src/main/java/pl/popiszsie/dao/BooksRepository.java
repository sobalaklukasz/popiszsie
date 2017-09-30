package pl.popiszsie.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.popiszsie.model.Book;

public interface BooksRepository extends JpaRepository<Book, Long> {}
