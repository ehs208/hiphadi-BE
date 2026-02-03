package hiphadi.menu.domain.product.dto;

import lombok.Getter;

@Getter
public class UpdateProductRequest {
	private String name;
	private String engName;
	private String description;
	private Long price;
	private Long categoryId;
	private String imageUrl;
}
