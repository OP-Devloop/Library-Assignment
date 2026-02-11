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

    //getAll
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    //getById
    public Book getById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No book found with id: " + id));
    }

    //getByIsbn
    public Book getByIsbn(Long isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    //createBook
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    //updateBook
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    //deleteBook
    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }
}
