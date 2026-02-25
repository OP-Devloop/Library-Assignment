package se.iths.oscarp.libraryassignment.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.iths.oscarp.libraryassignment.exception.BookValidationException;
import se.iths.oscarp.libraryassignment.model.Book;

class BookValidatorTest {
    BookValidator bookValidator;

    @BeforeEach
    void setUp() {
        bookValidator = new BookValidator();
    }

    @Test
    void Validate_WithNullBook() {
        Assertions.assertThrows(BookValidationException.class,
                () -> bookValidator.validate(new Book()));
    }

    @Test
    void Validate_WithNullTitle() {
        Book book = new Book();
        book.setAuthor("Sven Nordqvist");

        book.setGenre("Bilderbok");
        book.setYear(1982);
        Assertions.assertThrows(BookValidationException.class,
                () -> bookValidator.validate(book));
    }

    @Test
    void Validate_WithTooLongTitle() {
        Book book = new Book();
        book.setAuthor("Sven Nordqvist");
        book.setTitle("Pappa med stor hatt kommer hem och bli arg");
        book.setGenre("Bilderbok");
        book.setYear(1982);
        Assertions.assertThrows(BookValidationException.class,
                () -> bookValidator.validate(book));
    }

    @Test
    void Validate_WithNullAuthor() {
        Book book = new Book();

        book.setTitle("Pappa med stor hatt");
        book.setGenre("Bilderbok");
        book.setYear(1982);
        Assertions.assertThrows(BookValidationException.class,
                () -> bookValidator.validate(book));
    }

    @Test
    void Validate_WithTooLongAuthor() {
        Book book = new Book();
        book.setAuthor("Sven Engbert Roshamn Nordqvist");
        book.setTitle("Pappa med stor hatt");
        book.setGenre("Bilderbok");
        book.setYear(1982);
        Assertions.assertThrows(BookValidationException.class,
                () -> bookValidator.validate(book));
    }

    @Test
    void Validate_WithTooLongGenre() {
        Book book = new Book();
        book.setAuthor("Sven Nordqvist");
        book.setTitle("Pappa med stor hatt");
        book.setGenre("Traditionellt animerad folksaga");
        book.setYear(1982);
        Assertions.assertThrows(BookValidationException.class,
                () -> bookValidator.validate(book));
    }

    @Test
    void Validate_WithNullGenre() {
        Book book = new Book();
        book.setAuthor("Sven Nordqvist");
        book.setTitle("Pappa med stor hatt");

        book.setYear(1982);
        Assertions.assertThrows(BookValidationException.class,
                () -> bookValidator.validate(book));
    }

    @Test
    void Validate_WithTooSmallYear() {
        Book book = new Book();
        book.setAuthor("Sven Nordqvist");
        book.setTitle("Pappa med stor hatt");
        book.setGenre("Bilderbok");
        book.setYear(21);
        Assertions.assertThrows(BookValidationException.class,
                () -> bookValidator.validate(book));
    }

    @Test
    void Validate_WithFutureYear() {
        Book book = new Book();
        book.setAuthor("Sven Nordqvist");
        book.setTitle("Pappa med stor hatt");
        book.setGenre("Bilderbok");
        book.setYear(3333);
        Assertions.assertThrows(BookValidationException.class,
                () -> bookValidator.validate(book));
    }
}