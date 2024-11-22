package hiphadi.menu.api.service.request;

import java.math.BigDecimal;

import hiphadi.menu.domain.product.ProductStatus;
import hiphadi.menu.domain.product.RecommendStatus;
import lombok.Getter;

@Getter
public class ProductRequest {
	private String name;
	private String description;
	private BigDecimal price;
	private String category;
	private ProductStatus status;
	private RecommendStatus isRecommend;
}
