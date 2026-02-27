package se.iths.oscarp.libraryassignment.validator;

import org.springframework.stereotype.Component;
import se.iths.oscarp.libraryassignment.exception.GameValidationException;
import se.iths.oscarp.libraryassignment.model.Game;

@Component
public class GameValidator {
    public void validate(Game game){
        validateTitle(game.getTitle());
        validateMake(game.getMake());
        validateGenre(game.getGenre());
        validateAge(game.getAge());
    }

    public void validateTitle(String title){
        if(title == null || title.isBlank()){
            throw new GameValidationException(("Title is null or empty"));
        }
    }
    public void validateMake(String make) {
        if (make == null || make.isBlank()){
            throw new GameValidationException(("Make is null or empty"));
        }
    }
    public void validateGenre(String genre) {
        if (genre == null || genre.isBlank()){
            throw new GameValidationException(("Genre is null or empty"));
        }
    }
    public void validateAge(int age){
        if (age < 0 || age > 100){
            throw new GameValidationException(("Age is negative"));
        }
    }
}
