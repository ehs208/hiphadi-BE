package hiphadi.menu.api.service.request;

import java.math.BigDecimal;

import hiphadi.menu.domain.product.ProductStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CreateProductDto {
	private String name;
	private String description;
	private BigDecimal price;
	private String category;
	private ProductStatus status;
}
