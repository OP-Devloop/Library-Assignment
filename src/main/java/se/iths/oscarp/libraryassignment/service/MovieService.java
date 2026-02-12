package se.iths.oscarp.libraryassignment.service;

import org.springframework.stereotype.Service;
import se.iths.oscarp.libraryassignment.exceptions.MovieNotFoundException;
import se.iths.oscarp.libraryassignment.model.Movie;
import se.iths.oscarp.libraryassignment.repository.MovieRepository;
import se.iths.oscarp.libraryassignment.validator.MovieValidator;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository repo;
    private final MovieValidator validator;

    public MovieService(MovieRepository repo, MovieValidator validator) {
        this.repo = repo;
        this.validator = validator;
    }

    public List<Movie> findAll() {
        return repo.findAll();
    }

    public Movie findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
    }

    public Movie save(Movie movie) {
        validator.validate(movie);
        return repo.save(movie);
    }

    public Movie update(Long id, Movie movie) {
        repo.findById(id).orElseThrow(() -> new MovieNotFoundException(id));

        validator.validate(movie);
        movie.setId(id);
        return repo.save(movie);
    }

    public void delete(Long id) {
        Movie movie = repo.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
        repo.delete(movie);
    }
}
