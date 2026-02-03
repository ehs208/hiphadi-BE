package hiphadi.menu.domain.product.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CategoryReorderRequest {
	@NotNull
	private List<Long> categoryIds;
}
