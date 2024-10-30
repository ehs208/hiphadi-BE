package hiphadi.menu.api.service.response;

import java.math.BigDecimal;

import hiphadi.menu.domain.product.Product;
import hiphadi.menu.domain.product.ProductStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProductListDto {
	private String name;
	private String description;
	private BigDecimal price;
	private String category;
	private ProductStatus status;

	public ProductListDto(String name, String description, BigDecimal price, String category, ProductStatus status) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
		this.status = status;
	}

	public ProductListDto(Product product) {
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice();
		this.category = product.getCategory();
		this.status = product.getStatus();
	}
}


