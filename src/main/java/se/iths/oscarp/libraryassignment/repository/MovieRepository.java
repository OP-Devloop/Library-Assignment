package se.iths.oscarp.libraryassignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.oscarp.libraryassignment.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
