package se.iths.oscarp.libraryassignment.validator;

import org.springframework.stereotype.Component;
import se.iths.oscarp.libraryassignment.exception.BookValidationException;
import se.iths.oscarp.libraryassignment.model.Book;

@Component
public class BookValidator {

    public void validate(Book book) {
        if (book == null) {
            throw new BookValidationException("Provided book is null");
        }
        validateTitle(book.getTitle());
        validateAuthor(book.getAuthor());
        validateGenre(book.getGenre());
        validateYear(book.getYear());
    }

    public void validateTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new BookValidationException("Title can not be empty");
        }
    }

    public void validateAuthor(String author) {
        if (author == null || author.isEmpty()) {
            throw new BookValidationException("Author can not be empty");
        }
    }

    public void validateGenre(String genre) {
        if (genre == null || genre.isEmpty()) {
            throw new BookValidationException("Genre can not be empty");
        }
    }

    public void validateYear(int year) {
        if (year <= 1800) {
            throw new BookValidationException("Year can not be less than 1800");
        }
    }

}
