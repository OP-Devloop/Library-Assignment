package se.iths.oscarp.libraryassignment.validator;

import org.springframework.stereotype.Component;
import se.iths.oscarp.libraryassignment.exception.AudiobookValidationException;
import se.iths.oscarp.libraryassignment.model.Audiobook;

@Component
public class AudiobookValidator {

    public void validate(Audiobook audiobook) {
        validateTitle(audiobook.getTitle());
        validateAuthor(audiobook.getAuthor());
        validateNarrator(audiobook.getNarrator());
        validateReleaseDate(audiobook.getReleaseDate());
        validateCategory(audiobook.getCategory());
    }

    private void validateTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new AudiobookValidationException("Title cannot be blank.");
        }
    }

    private void validateAuthor(String author) {
        if (author == null || author.isBlank()) {
            throw new AudiobookValidationException("Author cannot be blank.");
        }
    }

    private void validateNarrator(String narrator) {
        if (narrator == null || narrator.isBlank()) {
            throw new AudiobookValidationException("Narrator cannot be blank.");
        }
    }

    private void validateReleaseDate(String releaseDate) {
        if (releaseDate == null || releaseDate.isBlank()) {
            throw new AudiobookValidationException("Invalid cannot be blank.");
        }
    }

    private void validateCategory(String category) {
        if (category == null || category.isBlank()) {
            throw new AudiobookValidationException("Category cannot be blank.");
        }
    }
}
