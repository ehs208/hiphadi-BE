package hiphadi.menu.api.service.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import hiphadi.menu.domain.product.ProductImg;
import hiphadi.menu.domain.product.Product;
import hiphadi.menu.domain.product.ProductStatus;
import hiphadi.menu.domain.product.RecommendStatus;
import lombok.Getter;

@Getter
public class ProductDetailResponse {
	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
	private String category;
	private ProductStatus status;
	private RecommendStatus isRecommend;
	private List<String> imgPath;

	public ProductDetailResponse(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice();
		this.imgPath = product.getProductImage().stream()
			.map(ProductImg::getUrl)
			.collect(Collectors.toList());
	}
}


