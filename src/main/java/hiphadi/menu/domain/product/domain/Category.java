package hiphadi.menu.domain.product.domain;

import hiphadi.menu.domain.BaseEntity;
import jakarta.persistence.Entity;
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
public class Category extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String categoryName;

	private String categoryEngName;

	@NotNull
	private Long priority;

	@Builder
	public Category(String categoryName, String categoryEngName, Long priority) {
		this.categoryName = categoryName;
		this.categoryEngName = categoryEngName;
		this.priority = priority;
	}

	public void updateInfo(String categoryName, String categoryEngName) {
		this.categoryName = categoryName;
		this.categoryEngName = categoryEngName;
	}

	public void updatePriority(Long priority) {
		this.priority = priority;
	}
}
