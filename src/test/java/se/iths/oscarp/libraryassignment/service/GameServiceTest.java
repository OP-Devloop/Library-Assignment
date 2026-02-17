package se.iths.oscarp.libraryassignment.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

class GameServiceTest {

    private GameRepository gameRepository;
    private GameValidator gameValidator;
    private GameService gameService;

    @BeforeEach
    void setUp() {
        gameRepository = mock(GameRepository.class);
        gameValidator = mock(GameValidator.class);
        gameService = new GameService(gameRepository, gameValidator);
    }

    @Test
    void getAllGames_returnsList() {
        Game game = new Game("Chess", "BoardCo", "Strategy", 8);
        when(gameRepository.findAll()).thenReturn(List.of(game));

        List<Game> games = gameService.getAllGames(null);

        assertEquals(1, games.size());
        assertEquals("Chess", games.get(0).getTitle());
        verify(gameRepository, times(1)).findAll();
    }

    @Test
    void getGameById_found() {
        Game game = new Game("Chess", "BoardCo", "Strategy", 8);
        game.setId(1L);
        when(gameRepository.findById(1L)).thenReturn(Optional.of(game));

        Game result = gameService.getGameById(1L);

        assertEquals(1L, result.getId());
        assertEquals("Chess", result.getTitle());
    }

    @Test
    void getGameById_notFound_throwsException() {
        when(gameRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(GameNotFoundException.class, () -> gameService.getGameById(1L));
    }

    @Test
    void createGame_valid_callsSave() {
        Game game = new Game("Chess", "BoardCo", "Strategy", 8);
        when(gameRepository.save(any(Game.class))).thenReturn(game);

        Game result = gameService.createGame(game);

        verify(gameValidator).validate(eq(game), any(BeanPropertyBindingResult.class));
        verify(gameRepository).save(game);
        assertEquals("Chess", result.getTitle());
    }

    @Test
    void createGame_invalid_throwsValidationException() {
        Game game = new Game(); // saknar title/make/genre/age

        // Simulera valideringsfel
        doAnswer(invocation -> {
            BeanPropertyBindingResult errors = invocation.getArgument(1);
            errors.rejectValue("title", "title.empty", "Title is empty");
            return null;
        }).when(gameValidator).validate(any(Game.class), any());

        assertThrows(GameValidationException.class, () -> gameService.createGame(game));
    }

    @Test
    void updateGame_valid_updatesAndSaves() {
        Game existing = new Game("Chess", "BoardCo", "Strategy", 8);
        existing.setId(1L);
        when(gameRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(gameRepository.save(any(Game.class))).thenReturn(existing);

        Game updated = new Game("Checkers", "BoardCo", "Strategy", 6);
        Game result = gameService.updateGame(1L, updated);

        assertEquals("Checkers", result.getTitle());
        assertEquals(6, result.getAge());
        verify(gameValidator, times(1)).validate(existing, any(BeanPropertyBindingResult.class));
        verify(gameRepository).save(existing);
    }

    @Test
    void updateGame_notFound_throwsException() {
        when(gameRepository.findById(1L)).thenReturn(Optional.empty());

        Game updated = new Game("Checkers", "BoardCo", "Strategy", 6);
        assertThrows(GameNotFoundException.class, () -> gameService.updateGame(1L, updated));
    }

    @Test
    void deleteGameById_found_callsDelete() {
        Game game = new Game("Chess", "BoardCo", "Strategy", 8);
        game.setId(1L);
        when(gameRepository.findById(1L)).thenReturn(Optional.of(game));

        gameService.deleteGameById(1L);

        verify(gameRepository).deleteById(1L);
    }

    @Test
    void deleteGameById_notFound_throwsException() {
        when(gameRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(GameNotFoundException.class, () -> gameService.deleteGameById(1L));
    }
}
