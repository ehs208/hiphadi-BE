package hiphadi.menu.domain.product;

import java.math.BigDecimal;

import hiphadi.menu.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;

	@Enumerated(EnumType.STRING)
	private ProductStatus status;

	@Enumerated(EnumType.STRING)
	private RecommendStatus isRecommend;

	@OneToOne
	@JoinColumn(name = "product_img_id")
	private ProductImg productImage;

	@Builder
	private Product(Long id, String name, String description, BigDecimal price, Category category, ProductStatus status,
		RecommendStatus isRecommend) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
		this.status = status;
		this.isRecommend = isRecommend;
	}

	public static Product create(String name, String description, BigDecimal price, Category category,
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

	public void update(String name, String description, BigDecimal price, Category category, ProductStatus status) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
		this.status = status;
	}
}
