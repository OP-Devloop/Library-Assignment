package se.iths.oscarp.libraryassignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.oscarp.libraryassignment.model.Game;

public interface GameRepository extends JpaRepository<Game, Long> {}
