package se.iths.oscarp.libraryassignment.validator;

import org.springframework.stereotype.Component;
import se.iths.oscarp.libraryassignment.exception.MovieValidationException;
import se.iths.oscarp.libraryassignment.model.Movie;

@Component
public class MovieValidator {

    public void validate(Movie movie) {
        validateTitle(movie.getTitle());
        validateDirector(movie.getDirector());
        validateReleaseYear(movie.getReleaseYear());
        validateGenre(movie.getGenre());
    }

    private void validateTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new MovieValidationException("Title cannot be blank.");
        }
    }

    private void validateDirector(String director) {
        if (director == null || director.isBlank()) {
            throw new MovieValidationException("Director cannot be blank.");
        }
    }

    private void validateReleaseYear(int releaseYear) {
        if (releaseYear < 1888 || releaseYear > 2027) {
            throw new MovieValidationException("Invalid release year.");
        }
    }

    private void validateGenre(String genre) {
        if (genre == null || genre.isBlank()) {
            throw new MovieValidationException("Genre cannot be blank");
        }
    }
}
