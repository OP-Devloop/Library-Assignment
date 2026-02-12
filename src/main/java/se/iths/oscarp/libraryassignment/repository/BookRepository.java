package se.iths.oscarp.libraryassignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.oscarp.libraryassignment.model.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> isbn(Long isbn);

    Book findByIsbn(Long isbn);
}
