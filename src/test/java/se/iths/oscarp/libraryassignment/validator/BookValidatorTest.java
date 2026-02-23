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
    void testValidateWithNullBook() {
        Assertions.assertThrows(BookValidationException.class,
                () -> bookValidator.validate(new Book()));
    }

    @Test
    void testValidateWithNullTitle() {
        Book book = new Book();
        book.setAuthor("Sven Nordqvist");

        book.setGenre("Bilderbok");
        book.setYear(1982);
        Assertions.assertThrows(BookValidationException.class,
                () -> bookValidator.validate(book));
    }

    @Test
    void testValidateWithTooLongTitle() {
        Book book = new Book();
        book.setAuthor("Sven Nordqvist");
        book.setTitle("Pappa med stor hatt kommer hem och bli arg");
        book.setGenre("Bilderbok");
        book.setYear(1982);
        Assertions.assertThrows(BookValidationException.class,
                () -> bookValidator.validate(book));
    }

    @Test
    void testValidateWithNullAuthor() {
        Book book = new Book();

        book.setTitle("Pappa med stor hatt");
        book.setGenre("Bilderbok");
        book.setYear(1982);
        Assertions.assertThrows(BookValidationException.class,
                () -> bookValidator.validate(book));
    }

    @Test
    void testValidateWithTooLongAuthor() {
        Book book = new Book();
        book.setAuthor("Sven Engbert Roshamn Nordqvist");
        book.setTitle("Pappa med stor hatt");
        book.setGenre("Bilderbok");
        book.setYear(1982);
        Assertions.assertThrows(BookValidationException.class,
                () -> bookValidator.validate(book));
    }

    @Test
    void testValidateWithTooLongGenre() {
        Book book = new Book();
        book.setAuthor("Sven Nordqvist");
        book.setTitle("Pappa med stor hatt");
        book.setGenre("Traditionellt animerad folksaga");
        book.setYear(1982);
        Assertions.assertThrows(BookValidationException.class,
                () -> bookValidator.validate(book));
    }

    @Test
    void testValidateWithNullGenre() {
        Book book = new Book();
        book.setAuthor("Sven Nordqvist");
        book.setTitle("Pappa med stor hatt");

        book.setYear(1982);
        Assertions.assertThrows(BookValidationException.class,
                () -> bookValidator.validate(book));
    }

    @Test
    void testValidateWithTooSmallYear() {
        Book book = new Book();
        book.setAuthor("Sven Nordqvist");
        book.setTitle("Pappa med stor hatt");
        book.setGenre("Bilderbok");
        book.setYear(21);
        Assertions.assertThrows(BookValidationException.class,
                () -> bookValidator.validate(book));
    }

    @Test
    void testValidateWithFutureYear() {
        Book book = new Book();
        book.setAuthor("Sven Nordqvist");
        book.setTitle("Pappa med stor hatt");
        book.setGenre("Bilderbok");
        book.setYear(3333);
        Assertions.assertThrows(BookValidationException.class,
                () -> bookValidator.validate(book));
    }
}