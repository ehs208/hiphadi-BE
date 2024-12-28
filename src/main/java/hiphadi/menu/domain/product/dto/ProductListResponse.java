package hiphadi.menu.domain.product.dto;

import hiphadi.menu.domain.menuAvailability.domain.MenuAvailability;
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
	private ProductStatus status;
	private RecommendStatus isRecommend;
	private String description;

	public ProductListResponse(MenuAvailability menu) {
		this.id = menu.getId();
		this.name = menu.getProduct().getName();
		this.engName= menu.getProduct().getEngName();
		this.price = menu.getPrice();
		this.description = menu.getProduct().getDescription();
		this.category = menu.getProduct().getCategory().getCategoryName();
		this.categoryEngName = menu.getProduct().getCategory().getCategoryEngName();
		this.status = menu.getProduct().getStatus();
		this.isRecommend = menu.getProduct().getIsRecommend();

	}
}

