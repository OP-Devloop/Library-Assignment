package se.iths.oscarp.libraryassignment.service;

import org.springframework.stereotype.Service;
import se.iths.oscarp.libraryassignment.repository.GameRepository;

@Service
public class GameService {
    private GameRepository gameRepository;

    public void setGameRepository(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }


}
