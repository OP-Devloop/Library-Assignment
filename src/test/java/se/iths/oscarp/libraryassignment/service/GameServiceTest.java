package se.iths.oscarp.libraryassignment.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.oscarp.libraryassignment.exception.GameNotFoundException;
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
    void findById_NonExistingId_ShouldThrowException() {
        when(gameRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(GameNotFoundException.class, () -> gameService.getGameById(1L));
    }

    @Test
    void save_WithValidGame_ShouldCallValidatorSaveGame() {
        Game game = new Game("Title", "Make", "Genre", 8);

        when(gameRepository.save(game)).thenReturn(game);

        Game result = gameService.save(game);

        verify(gameValidator, times(1)).validate(game);
        verify(gameRepository, times(1)).save(game);
        assertNotNull(result);
    }

    @Test
    void update_WithExistingId_ShouldUpdateAndSave(){
        Game existingGame = new Game("Title", "Make", "Genre", 8);
        Game updatedGame = new Game("Title", "Make", "Genre", 12);

        when(gameRepository.findById(1L)).thenReturn(Optional.of(existingGame));
        when(gameRepository.save(updatedGame)).thenReturn(updatedGame);

        Game result = gameService.updateGame(1L, updatedGame);

        assertEquals(1L, updatedGame.getId());
        verify(gameValidator, times(1)).validate(updatedGame);
        verify(gameRepository, times(1)).save(updatedGame);
    }

    @Test
    void delete_WithExistingId_ShouldDeleteGame(){
        Game game = new Game("Title", "Make", "Genre", 8);

        when(gameRepository.findById(1L)).thenReturn(Optional.of(game));
        gameService.deleteGameById(1L);

        verify(gameRepository, times(1)).deleteById(1L);
    }
}
