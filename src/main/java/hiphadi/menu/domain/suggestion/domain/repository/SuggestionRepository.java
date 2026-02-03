package hiphadi.menu.domain.suggestion.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hiphadi.menu.domain.suggestion.domain.Suggestion;

public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {

	List<Suggestion> findAllByOrderByCreatedAtDesc();
}
