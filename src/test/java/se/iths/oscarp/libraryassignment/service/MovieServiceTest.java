package se.iths.oscarp.libraryassignment.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.oscarp.libraryassignment.exception.MovieNotFoundException;
import se.iths.oscarp.libraryassignment.model.Movie;
import se.iths.oscarp.libraryassignment.repository.MovieRepository;
import se.iths.oscarp.libraryassignment.validator.MovieValidator;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private MovieValidator movieValidator;

    @InjectMocks
    private MovieService movieService;

    @Test
    void findAll_ShouldReturnListOfMovies() {
        Movie movie = new Movie("Title", "Director", 2020, "Genre");
        when(movieRepository.findAll()).thenReturn(List.of(movie));

        List<Movie> result = movieService.findAll();

        assertEquals(1, result.size());
        assertEquals("Title", result.get(0).getTitle());
        verify(movieRepository, times(1)).findAll();
    }

    @Test
    void findById_WithExistingId_ShouldReturnMovie() {
        Movie movie = new Movie("Title", "Director", 2020, "Genre");
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));

        Movie result = movieService.findById(1L);

        assertNotNull(result);
        assertEquals("Title", result.getTitle());
    }

    @Test
    void findById_WithNonExistingId_ShouldThrowException() {
        when(movieRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(MovieNotFoundException.class, () -> movieService.findById(1L));
    }

    @Test
    void save_WithValidMovie_ShouldCallValidatorAndSave() {
        Movie movie = new Movie("Title", "Director", 2020, "Genre");
        when(movieRepository.save(movie)).thenReturn(movie);

        Movie result = movieService.save(movie);

        verify(movieValidator, times(1)).validate(movie);
        verify(movieRepository, times(1)).save(movie);
        assertNotNull(result);
    }

    @Test
    void update_WithExistingId_ShouldUpdateAndSave() {
        Movie existingMovie = new Movie("Old Title", "Director", 2020, "Genre");
        Movie updatedMovie = new Movie("New Title", "Director", 2020, "Genre");
        
        when(movieRepository.findById(1L)).thenReturn(Optional.of(existingMovie));
        when(movieRepository.save(updatedMovie)).thenReturn(updatedMovie);

        Movie result = movieService.update(1L, updatedMovie);

        assertEquals(1L, updatedMovie.getId());
        verify(movieValidator, times(1)).validate(updatedMovie);
        verify(movieRepository, times(1)).save(updatedMovie);
    }

    @Test
    void delete_WithExistingId_ShouldDeleteMovie() {
        Movie movie = new Movie();
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));

        movieService.delete(1L);

        verify(movieRepository, times(1)).delete(movie);
    }

    @Test
    void delete_WithNonExistingId_ShouldThrowException() {
        when(movieRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(MovieNotFoundException.class, () -> movieService.delete(1L));
        verify(movieRepository, never()).delete(any());
    }
}
