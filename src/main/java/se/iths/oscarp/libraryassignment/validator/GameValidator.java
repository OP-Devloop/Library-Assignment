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

    }

    public void validate(Game game) {
    }
}
