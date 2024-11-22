package hiphadi.menu.domain.product;

import java.math.BigDecimal;

import hiphadi.menu.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Product extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String name;

	private String description;

	@NotNull
	private BigDecimal price;

	@NotNull
	private String category;

	@Enumerated(EnumType.STRING)
	private ProductStatus status;

	@Enumerated(EnumType.STRING)
	private RecommendStatus isRecommend;

	@Builder
	private Product(Long id, String name, String description, BigDecimal price, String category, ProductStatus status,
		RecommendStatus isRecommend) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
		this.status = status;
		this.isRecommend = isRecommend;
	}

	public static Product create(String name, String description, BigDecimal price, String category,
		ProductStatus status, RecommendStatus isRecommend) {
		return Product.builder()
			.name(name)
			.description(description)
			.price(price)
			.category(category)
			.status(status)
			.isRecommend(isRecommend)
			.build();
	}

	public void update(String name, String description, BigDecimal price, String category, ProductStatus status) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
		this.status = status;
	}
}
