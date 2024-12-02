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
public class ProductListResponse {
	private Long id;
	private String name;
	private BigDecimal price;
	private String category;
	private ProductStatus status;
	private RecommendStatus isRecommend;
	private String description;

	public ProductListResponse(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.price = product.getPrice();
		this.description = product.getDescription();
		this.category = product.getCategory().getCategoryName();
		this.status = product.getStatus();
		this.isRecommend = product.getIsRecommend();

	}
}


