package se.iths.oscarp.libraryassignment.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BeanPropertyBindingResult;
import se.iths.oscarp.libraryassignment.exception.GameNotFoundException;
import se.iths.oscarp.libraryassignment.exception.GameValidationException;
import se.iths.oscarp.libraryassignment.model.Game;
import se.iths.oscarp.libraryassignment.repository.GameRepository;
import se.iths.oscarp.libraryassignment.validator.GameValidator;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock
    private GameRepository gameRepository;
    @Mock
    private GameValidator gameValidator;
    @InjectMocks
    private GameService gameService;

    @Test
    void findAll_ReturnsListOfGames() {
        Game game = new Game("Title", "Make", "Genre", 8);
        when(gameRepository.findAll()).thenReturn(List.of(game));

        List<Game> result = gameService.findAll();

        assertEquals(1, result.size());
        assertEquals("Title", result.get(0).getTitle());
        verify(gameRepository, times(1)).findAll();
    }

    @Test
    void findById_ExistsReturnsGame() {
        Game game = new Game("Title", "Make", "Genre", 8);
        when(gameRepository.findById(1L)).thenReturn(Optional.of(game));
        Game result = gameService.getGameById(1L);
        assertNotNull(result);
        assertEquals(game, result);
    }

    @Test
    void findById_NonExistingIdReturnsNull() {
        when(gameRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(GameNotFoundException.class, () -> gameService.getGameById(1L));
    }
}
