package hiphadi.menu.api.service.response;

import java.math.BigDecimal;

import hiphadi.menu.domain.product.Category;
import hiphadi.menu.domain.product.Product;
import hiphadi.menu.domain.product.ProductStatus;
import hiphadi.menu.domain.product.RecommendStatus;
import lombok.Getter;

@Getter
public class ProductResponse {
	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
	private String category;
	private ProductStatus status;
	private RecommendStatus isRecommend;
	private String imgPath;

	public ProductResponse(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice();
		this.category = product.getCategory().getCategoryName();
		this.status = product.getStatus();
		this.isRecommend = product.getIsRecommend();
		this.imgPath = product.getProductImage().getUrl();
	}
}


