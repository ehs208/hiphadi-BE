package hiphadi.menu.api.service.response;

import java.math.BigDecimal;

import hiphadi.menu.domain.product.Product;
import hiphadi.menu.domain.menuAvailability.MenuAvailability;
import hiphadi.menu.domain.product.ProductStatus;
import hiphadi.menu.domain.product.RecommendStatus;
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


