package se.iths.oscarp.libraryassignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.oscarp.libraryassignment.model.Audiobook;

@Repository
public interface AudiobookRepository extends JpaRepository<Audiobook, Long> {
}
