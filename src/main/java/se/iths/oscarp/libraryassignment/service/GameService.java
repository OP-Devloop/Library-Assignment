package se.iths.oscarp.libraryassignment.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import se.iths.oscarp.libraryassignment.exceptions.GameNotFoundException;
import se.iths.oscarp.libraryassignment.exceptions.GameValidationException;
import se.iths.oscarp.libraryassignment.model.Game;
import se.iths.oscarp.libraryassignment.repository.GameRepository;
import se.iths.oscarp.libraryassignment.validation.GameValidator;

import java.util.List;

@Service
public class GameService {
    private GameRepository gameRepository;
    private GameValidator gameValidator;

    public GameService(GameRepository gameRepository,
                                  GameValidator gameValidator) {
        this.gameRepository = gameRepository;
        this.gameValidator = gameValidator;
    }

    public List<Game> getAllGames(String title) {
        return gameRepository.findAll();
    }

    public Game getGameById(Long id){
        return gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException("Game with id " + id + " not found"));
    }

    public Game createGame(Game game) {
        Errors errors = new BeanPropertyBindingResult(game, "game");
        gameValidator.validate(game, errors);
        if (errors.hasErrors()) {
            throw new GameValidationException("Error: " + errors.getAllErrors());
        }
        return gameRepository.save(game);
    }

    public Game updateGame(Long id, Game updatedGame) {
        Game existingGame = gameRepository.findById(id)
                .orElseThrow(() ->
                        new GameNotFoundException("Game with id " + id + " not found"));

        existingGame.setTitle(updatedGame.getTitle());
        existingGame.setMake(updatedGame.getMake());
        existingGame.setGenre(updatedGame.getGenre());
        existingGame.setAge(updatedGame.getAge());

        Errors errors = new BeanPropertyBindingResult(existingGame, "game");
        gameValidator.validate(existingGame, errors);
        if(errors.hasErrors()){
            throw new GameValidationException(errors.getAllErrors().toString());
        }
        return gameRepository.save(existingGame);
    }

    public void deleteGameById(Long id) {
        Game game = gameRepository.findById(id)
                        .orElseThrow(() -> new GameNotFoundException("Game with id " + id + " not found"));
        gameRepository.deleteById(id);
    }
}
