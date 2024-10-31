package hiphadi.menu.api.service.response;

import java.math.BigDecimal;

import hiphadi.menu.domain.product.Product;
import hiphadi.menu.domain.product.ProductStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProductResponse {
	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
	private String category;
	private ProductStatus status;

	public ProductResponse(Long id, String name, String description, BigDecimal price, String category, ProductStatus status) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
		this.status = status;
	}

	public ProductResponse(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice();
		this.category = product.getCategory();
		this.status = product.getStatus();
	}
}


