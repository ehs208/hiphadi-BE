package hiphadi.menu.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CategoryRequest {
	@NotBlank
	private String categoryName;
	private String categoryEngName;
}
