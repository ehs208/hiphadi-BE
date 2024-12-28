package hiphadi.menu.domain.product.dto;

import java.math.BigDecimal;

import hiphadi.menu.domain.product.domain.Category;
import hiphadi.menu.domain.product.domain.ProductStatus;
import hiphadi.menu.domain.product.domain.RecommendStatus;
import lombok.Getter;

@Getter
public class ProductRequest {
	private String name;
	private String description;
	private BigDecimal price;
	private Category category;
	private ProductStatus status;
	private RecommendStatus isRecommend;
}
