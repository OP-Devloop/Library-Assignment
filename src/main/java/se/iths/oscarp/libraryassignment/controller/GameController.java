package se.iths.oscarp.libraryassignment.controller;

import org.springframework.web.bind.annotation.*;
import se.iths.oscarp.libraryassignment.model.Game;
import se.iths.oscarp.libraryassignment.service.GameService;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public List<Game> getAllGames() {
        return gameService.getAllGames(null);
    }

    @PostMapping
    public Game createGame(@RequestBody Game game) {
        return gameService.createGame(game);
    }

    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable Long id) {
        gameService.deleteGameById(id);
    }
}
