package hiphadi.menu.domain.statistics.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "product_click_daily", uniqueConstraints = {
	@UniqueConstraint(name = "uk_product_date", columnNames = {"product_id", "click_date"})
})
public class ProductClickDaily {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "product_id", nullable = false)
	private Long productId;

	@Column(name = "click_date", nullable = false)
	private LocalDate clickDate;

	@Column(name = "click_count", nullable = false)
	private int clickCount;

	public static ProductClickDaily create(Long productId, LocalDate clickDate) {
		ProductClickDaily entity = new ProductClickDaily();
		entity.productId = productId;
		entity.clickDate = clickDate;
		entity.clickCount = 1;
		return entity;
	}

	public void incrementClickCount() {
		this.clickCount++;
	}
}
