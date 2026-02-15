package se.iths.oscarp.libraryassignment.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.iths.oscarp.libraryassignment.exception.MovieValidationException;
import se.iths.oscarp.libraryassignment.model.Movie;

import static org.junit.jupiter.api.Assertions.*;

class MovieValidatorTest {

    private MovieValidator movieValidator;

    @BeforeEach
    void setUp() {
        movieValidator = new MovieValidator();
    }

    @Test
    void validate_WithValidMovie_ShouldNotThrowException() {
        Movie movie = new Movie("Inception", "Christopher Nolan", 2010, "Sci-Fi");
        assertDoesNotThrow(() -> movieValidator.validate(movie));
    }

    @Test
    void validate_WithBlankTitle_ShouldThrowException() {
        Movie movie = new Movie("", "Director", 2020, "Genre");
        MovieValidationException exception = assertThrows(MovieValidationException.class, 
            () -> movieValidator.validate(movie));
        assertEquals("Title cannot be blank.", exception.getMessage());
    }

    @Test
    void validate_WithNullTitle_ShouldThrowException() {
        Movie movie = new Movie(null, "Director", 2020, "Genre");
        assertThrows(MovieValidationException.class, () -> movieValidator.validate(movie));
    }

    @Test
    void validate_WithBlankDirector_ShouldThrowException() {
        Movie movie = new Movie("Title", " ", 2020, "Genre");
        MovieValidationException exception = assertThrows(MovieValidationException.class, 
            () -> movieValidator.validate(movie));
        assertEquals("Director cannot be blank.", exception.getMessage());
    }

    @Test
    void validate_WithInvalidReleaseYear_TooOld_ShouldThrowException() {
        Movie movie = new Movie("Title", "Director", 1800, "Genre");
        assertThrows(MovieValidationException.class, () -> movieValidator.validate(movie));
    }

    @Test
    void validate_WithInvalidReleaseYear_Future_ShouldThrowException() {
        Movie movie = new Movie("Title", "Director", 2030, "Genre");
        assertThrows(MovieValidationException.class, () -> movieValidator.validate(movie));
    }

    @Test
    void validate_WithBlankGenre_ShouldThrowException() {
        Movie movie = new Movie("Title", "Director", 2020, "");
        assertThrows(MovieValidationException.class, () -> movieValidator.validate(movie));
    }
}
