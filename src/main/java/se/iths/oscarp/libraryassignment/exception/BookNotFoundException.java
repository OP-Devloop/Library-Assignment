package se.iths.oscarp.libraryassignment.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) {
        super("Could not fnd book with id " + id);
    }
}
