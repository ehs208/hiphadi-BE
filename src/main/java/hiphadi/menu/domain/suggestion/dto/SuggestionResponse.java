package hiphadi.menu.domain.suggestion.dto;

import java.time.LocalDateTime;

import hiphadi.menu.domain.suggestion.domain.Suggestion;
import lombok.Getter;

@Getter
public class SuggestionResponse {

	private Long id;
	private String content;
	private LocalDateTime createdAt;

	public SuggestionResponse(Suggestion suggestion) {
		this.id = suggestion.getId();
		this.content = suggestion.getContent();
		this.createdAt = suggestion.getCreatedAt();
	}
}
