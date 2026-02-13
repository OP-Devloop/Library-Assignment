package se.iths.oscarp.libraryassignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.iths.oscarp.libraryassignment.model.Game;
import se.iths.oscarp.libraryassignment.repository.GameRepository;
import se.iths.oscarp.libraryassignment.service.GameService;

import java.util.List;

@Controller
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;


    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public String getAllGames(Model model) {
        List<Game> games = gameService.getAllGames();
        model.addAttribute("games", games);
        return "games";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("game", new Game());
        return "create-game";
    }

    @PostMapping
    public String createGame(@ModelAttribute Game game) {
        gameService.createGame(game);
        return "redirect:/games";
    }

}
