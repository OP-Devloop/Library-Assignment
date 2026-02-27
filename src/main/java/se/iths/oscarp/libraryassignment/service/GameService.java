package se.iths.oscarp.libraryassignment.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import se.iths.oscarp.libraryassignment.exception.GameNotFoundException;
import se.iths.oscarp.libraryassignment.exception.GameValidationException;
import se.iths.oscarp.libraryassignment.model.Game;
import se.iths.oscarp.libraryassignment.repository.GameRepository;
import se.iths.oscarp.libraryassignment.validator.GameValidator;

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

    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    public Game getGameById(Long id){
        return gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException("Game with id " + id + " not found"));
    }

    public Game save(Game game) {
        Errors errors = new BeanPropertyBindingResult(game, "game");
        gameValidator.validate(game);
        if (errors.hasErrors()) {
            throw new GameValidationException("Error: " + errors.getAllErrors());
        }
        return gameRepository.save(game);
    }

    public Game updateGame(Long id, Game updatedGame) {
        gameRepository.findById(id).orElseThrow(() -> new GameNotFoundException("Game with id " + id + " not found"));
        gameValidator.validate(updatedGame);
        updatedGame.setId(id);
        return gameRepository.save(updatedGame);
    }

    public void deleteGameById(Long id) {
        Game game = gameRepository.findById(id)
                        .orElseThrow(() -> new GameNotFoundException("Game with id " + id + " not found"));
        gameRepository.deleteById(id);
    }
}
