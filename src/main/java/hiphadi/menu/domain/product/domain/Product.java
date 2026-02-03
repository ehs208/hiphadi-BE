package hiphadi.menu.domain.product.domain;

import hiphadi.menu.domain.BaseEntity;
import hiphadi.menu.domain.image.domain.ImageFile;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

	private Long customOrder;

	@NotNull
	private String name;

	private String engName;

	private String description;

	private Long singlePrice;

	private Long bottlePrice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;

	@Enumerated(EnumType.STRING)
	private ProductStatus status;

	@Enumerated(EnumType.STRING)
	private RecommendStatus isRecommend;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "image_file_id")
	private ImageFile image;

	@Builder
	public Product(String name, String engName, String description, Long singlePrice,
				   Long bottlePrice, Category category, Long customOrder) {
		this.name = name;
		this.engName = engName;
		this.description = description;
		this.singlePrice = singlePrice;
		this.bottlePrice = bottlePrice;
		this.category = category;
		this.customOrder = customOrder;
		this.status = ProductStatus.SALE;
		this.isRecommend = RecommendStatus.NORMAL;
	}

	public void updateInfo(String name, String engName, String description, Long singlePrice,
						   Long bottlePrice, Category category) {
		this.name = name;
		this.engName = engName;
		this.description = description;
		this.singlePrice = singlePrice;
		this.bottlePrice = bottlePrice;
		this.category = category;
	}

	public void toggleStatus() {
		this.status = (this.status == ProductStatus.SALE) ? ProductStatus.SOLD_OUT : ProductStatus.SALE;
	}

	public void toggleRecommend() {
		this.isRecommend = (this.isRecommend == RecommendStatus.RECOMMEND) ? RecommendStatus.NORMAL : RecommendStatus.RECOMMEND;
	}

	public void updateCustomOrder(Long customOrder) {
		this.customOrder = customOrder;
	}

	public void updateImage(ImageFile image) {
		this.image = image;
	}
}
