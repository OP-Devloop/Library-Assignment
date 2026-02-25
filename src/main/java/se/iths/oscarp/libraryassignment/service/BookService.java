package se.iths.oscarp.libraryassignment.service;

import org.springframework.stereotype.Service;
import se.iths.oscarp.libraryassignment.exception.BookNotFoundException;
import se.iths.oscarp.libraryassignment.exception.BookValidationException;
import se.iths.oscarp.libraryassignment.model.Book;
import se.iths.oscarp.libraryassignment.repository.BookRepository;
import se.iths.oscarp.libraryassignment.validator.BookValidator;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookValidator bookValidator;

    public BookService(BookRepository bookRepository, BookValidator bookValidator) {
        this.bookRepository = bookRepository;
        this.bookValidator = bookValidator;
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book save(Book book) throws BookValidationException {
        bookValidator.validate(book);
        return bookRepository.save(book);
    }

    public Book getById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public Book update(Long id, Book book) throws BookValidationException {
        book.setId(id);
        bookValidator.validate(book);
        return bookRepository.save(book);
    }

    public void delete(Long id) {
        bookRepository.delete(getById(id));
    }
}
