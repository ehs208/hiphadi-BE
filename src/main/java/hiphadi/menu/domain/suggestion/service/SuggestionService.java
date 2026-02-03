package hiphadi.menu.domain.suggestion.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hiphadi.menu.domain.suggestion.domain.Suggestion;
import hiphadi.menu.domain.suggestion.domain.repository.SuggestionRepository;
import hiphadi.menu.global.exception.CustomException;
import hiphadi.menu.global.exception.ErrorCode;
import hiphadi.menu.domain.suggestion.dto.CreateSuggestionRequest;
import hiphadi.menu.domain.suggestion.dto.SuggestionResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SuggestionService {

	private final SuggestionRepository suggestionRepository;

	@Transactional
	public SuggestionResponse createSuggestion(CreateSuggestionRequest request) {
		Suggestion suggestion = Suggestion.builder()
			.content(request.getContent())
			.build();

		Suggestion saved = suggestionRepository.save(suggestion);
		return new SuggestionResponse(saved);
	}

	@Transactional(readOnly = true)
	public List<SuggestionResponse> getAllSuggestions() {
		return suggestionRepository.findAllByOrderByCreatedAtDesc()
			.stream()
			.map(SuggestionResponse::new)
			.collect(Collectors.toList());
	}

	@Transactional
	public void deleteSuggestion(Long id) {
		Suggestion suggestion = suggestionRepository.findById(id)
			.orElseThrow(() -> new CustomException(ErrorCode.SUGGESTION_NOT_FOUND));
		suggestionRepository.delete(suggestion);
	}
}
