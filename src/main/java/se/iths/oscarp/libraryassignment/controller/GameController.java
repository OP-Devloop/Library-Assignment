package se.iths.oscarp.libraryassignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.iths.oscarp.libraryassignment.model.Game;
import se.iths.oscarp.libraryassignment.service.GameService;

import java.util.List;

@Controller
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("game", new Game());
        return "create-game";
    }

    @PostMapping("/new")
    public String createGame(@ModelAttribute Game game){
        gameService.createGame(game);
        return "redirect:/games";
    }

    @GetMapping
    public String listGames(Model model){
        model.addAttribute("games", gameService.findAll());
        return "games";
    }

    @GetMapping("/delete/{id}")
    public String deleteGame(@PathVariable Long id) {
        gameService.deleteGameById(id);
        return "redirect:/games";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model){
        Game game = gameService.getGameById(id);
        model.addAttribute("game", game);
        return "edit-game";
    }

    @PostMapping("/update")
    public String updateGame(@ModelAttribute Game game){
        gameService.updateGame(game.getId(), game);
        return "redirect:/games";
    }
}
