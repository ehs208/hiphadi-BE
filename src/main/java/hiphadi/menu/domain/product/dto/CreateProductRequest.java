package hiphadi.menu.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateProductRequest {
	@NotBlank
	private String name;
	private String engName;
	private String description;
	private Long singlePrice;
	private Long bottlePrice;
	@NotNull
	private Long categoryId;
	private String imageUrl;
}
