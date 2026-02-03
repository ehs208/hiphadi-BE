package hiphadi.menu.domain.product.dto;

import hiphadi.menu.domain.product.domain.Product;
import hiphadi.menu.domain.product.domain.ProductStatus;
import hiphadi.menu.domain.product.domain.RecommendStatus;
import lombok.Getter;

@Getter
public class ProductDetailResponse {
	private Long id;
	private String name;
	private String engName;
	private String description;
	private Long price;
	private Long categoryId;
	private String category;
	private ProductStatus status;
	private RecommendStatus isRecommend;
	private String imageUrl;

	public ProductDetailResponse(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.engName = product.getEngName();
		this.description = product.getDescription();
		this.price = product.getPrice();
		this.categoryId = product.getCategory().getId();
		this.category = product.getCategory().getCategoryName();
		this.status = product.getStatus();
		this.isRecommend = product.getIsRecommend();
		this.imageUrl = product.getImage() != null ? product.getImage().getUrl() : null;
	}
}
