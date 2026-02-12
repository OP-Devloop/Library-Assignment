package se.iths.oscarp.libraryassignment.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.iths.oscarp.libraryassignment.exception.AudiobookValidationException;
import se.iths.oscarp.libraryassignment.model.Audiobook;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AudiobookValidatorTest {

    private AudiobookValidator audiobookValidator;

    @BeforeEach
    void setup() {
        audiobookValidator = new AudiobookValidator();
    }

    @Test
    void validate_shouldNotThrow_whenAudiobookIsValid() {
        Audiobook audiobook = new Audiobook(
                1L,
                "Title",
                "Fiction",
                "Author",
                "Narrator",
                "2024-01-01"
        );

        assertDoesNotThrow(() -> audiobookValidator.validate(audiobook));
    }

    @Test
    void validate_shouldThrow_whenAudiobookIsNull() {
        assertThrows(AudiobookValidationException.class,
                () -> audiobookValidator.validate(null));
    }

    @Test
    void validate_shouldThrow_whenTitleIsBlank() {
        Audiobook audiobook = new Audiobook(
                1L,
                "",
                "Fiction",
                "Author",
                "Narrator",
                "2024-01-01"
        );

        assertThrows(AudiobookValidationException.class,
                () -> audiobookValidator.validate(audiobook));
    }

    @Test
    void validate_shouldThrow_whenCategoryIsBlank() {
        Audiobook audiobook = new Audiobook(
                1L,
                "Title",
                "",
                "Author",
                "Narrator",
                "2024-01-01"
        );

        assertThrows(AudiobookValidationException.class,
                () -> audiobookValidator.validate(audiobook));
    }

    @Test
    void validate_shouldThrow_whenAuthorIsBlank() {
        Audiobook audiobook = new Audiobook(
                1L,
                "Title",
                "Fiction",
                "",
                "Narrator",
                "2024-01-01"
        );

        assertThrows(AudiobookValidationException.class,
                () -> audiobookValidator.validate(audiobook));
    }

    @Test
    void validate_shouldThrow_whenNarratorIsBlank() {
        Audiobook audiobook = new Audiobook(
                1L,
                "Title",
                "Fiction",
                "Author",
                "",
                "2024-01-01"
        );

        assertThrows(AudiobookValidationException.class,
                () -> audiobookValidator.validate(audiobook));
    }

    @Test
    void validate_shouldThrow_whenReleaseDateIsBlank() {
        Audiobook audiobook = new Audiobook(
                1L,
                "Title",
                "Fiction",
                "Author",
                "Narrator",
                ""
        );

        assertThrows(AudiobookValidationException.class,
                () -> audiobookValidator.validate(audiobook));
    }
}

