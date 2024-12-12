package hiphadi.menu.api.service.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import hiphadi.menu.domain.menuAvailability.MenuAvailability;
import hiphadi.menu.domain.product.ProductImg;
import hiphadi.menu.domain.product.Product;
import lombok.Getter;

@Getter
public class ProductDetailResponse {
	private Long id;
	private String name;
	private String description;
	private Long price;
	private List<String> imgPath;

	public ProductDetailResponse(MenuAvailability menu) {
		this.id = menu.getId();
		this.name = menu.getProduct().getName();
		this.description = menu.getProduct().getDescription();
		this.price = menu.getPrice();
		this.imgPath = menu.getProduct().getProductImage().stream()
			.map(ProductImg::getUrl)
			.collect(Collectors.toList());
	}
}


