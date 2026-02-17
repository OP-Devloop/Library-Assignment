package se.iths.oscarp.libraryassignment.service;

import org.springframework.stereotype.Service;
import se.iths.oscarp.libraryassignment.model.Book;
import se.iths.oscarp.libraryassignment.repository.BookRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book getBook(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No book found with id: " + id));
    }

    public Book updateBook(Long id, Book book) {
        book.setId(id);
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.delete(getBook(id));
    }
}
