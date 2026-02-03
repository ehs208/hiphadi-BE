package hiphadi.menu.domain.suggestion.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hiphadi.menu.domain.suggestion.dto.CreateSuggestionRequest;
import hiphadi.menu.domain.suggestion.dto.SuggestionResponse;
import hiphadi.menu.domain.suggestion.service.SuggestionService;
import hiphadi.menu.global.response.GlobalResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/suggestions")
@RequiredArgsConstructor
public class SuggestionController {

	private final SuggestionService suggestionService;

	@PostMapping
	public GlobalResponseDto<SuggestionResponse> createSuggestion(
		@Valid @RequestBody CreateSuggestionRequest request) {
		return GlobalResponseDto.success(suggestionService.createSuggestion(request), 201);
	}
}
