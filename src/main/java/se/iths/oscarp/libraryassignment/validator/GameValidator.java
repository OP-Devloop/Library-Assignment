package se.iths.oscarp.libraryassignment.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import se.iths.oscarp.libraryassignment.model.Game;

@Component
public class GameValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Game.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Game game = (Game) target;

        if (game.getTitle() == null || game.getTitle().isBlank()) {
            errors.rejectValue("title", "title.empty", "Title must not be empty");
        }

        if (game.getMake() == null || game.getMake().isBlank()) {
            errors.rejectValue("make", "make.empty", "Make must not be empty");
        }

        if (game.getGenre() == null || game.getGenre().isBlank()) {
            errors.rejectValue("genre", "genre.empty", "Genre must not be empty");
        }

        if (game.getAge() < 0 || game.getAge() > 99) {
            errors.rejectValue("age", "age.invalid", "Age must be between 0 and 99");
        }
    }
}
