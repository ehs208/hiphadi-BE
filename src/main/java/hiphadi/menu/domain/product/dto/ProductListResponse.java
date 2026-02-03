package hiphadi.menu.domain.product.dto;

import hiphadi.menu.domain.product.domain.Product;
import hiphadi.menu.domain.product.domain.ProductStatus;
import hiphadi.menu.domain.product.domain.RecommendStatus;
import lombok.Getter;

@Getter
public class ProductListResponse {
	private Long id;
	private String name;
	private String engName;
	private Long price;
	private String category;
	private String categoryEngName;
	private Long categoryId;
	private ProductStatus status;
	private RecommendStatus isRecommend;
	private String description;

	public ProductListResponse(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.engName = product.getEngName();
		this.price = product.getPrice();
		this.description = product.getDescription();
		this.category = product.getCategory().getCategoryName();
		this.categoryEngName = product.getCategory().getCategoryEngName();
		this.categoryId = product.getCategory().getId();
		this.status = product.getStatus();
		this.isRecommend = product.getIsRecommend();
	}
}
