package se.iths.oscarp.libraryassignment.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.iths.oscarp.libraryassignment.exception.GameValidationException;
import se.iths.oscarp.libraryassignment.model.Game;

import static org.junit.jupiter.api.Assertions.*;

public class GameValidatorTest {
    private GameValidator gameValidator;

    @BeforeEach
    public void setUp() {
        gameValidator = new GameValidator();
    }

    @Test
    void validate_WithValidGame_ShouldNotThrowException() {
        Game game = new Game("Chess", "Traditional", "Strategy", 0);
        assertDoesNotThrow(() -> gameValidator.validate(game));
    }

    @Test
    void validate_WithBlankTitle_ShouldThrowException() {
        Game game = new Game("", "Traditional", "Strategy", 12);
        GameValidationException exception = assertThrows(GameValidationException.class, () -> gameValidator.validate(game));
        assertEquals("Title is null or empty", exception.getMessage());
    }

    @Test
    void validate_WithNullTitle_ShouldThrowException() {
        Game game = new Game(null, "Traditional", "Strategy", 12);
        assertThrows(GameValidationException.class, () -> gameValidator.validate(game));
    }

    @Test
    void validate_WithBlankMake_ShouldThrowException() {
        Game game = new Game("Chess", "", "Strategy", 12);
        GameValidationException exception = assertThrows(GameValidationException.class, () -> gameValidator.validate(game));
        assertEquals("Make is null or empty", exception.getMessage());
    }

    @Test
    void validate_WithNullMake_ShouldThrowException() {
        Game game = new Game("Chess", null, "Strategy", 12);
        GameValidationException exception = assertThrows(GameValidationException.class, () -> gameValidator.validate(game));
        assertEquals("Make is null or empty", exception.getMessage());
    }

    @Test
    void validate_WithInvalidAge_ShouldThrowException(){
        Game game = new Game("Chess", "Traditional", "Strategy", -2);
        GameValidationException exception = assertThrows(GameValidationException.class, () -> gameValidator.validate(game));
        assertEquals("Age is negative", exception.getMessage());
    }
}
