package hiphadi.menu.domain.suggestion.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateSuggestionRequest {

	@NotBlank
	private String content;
}
