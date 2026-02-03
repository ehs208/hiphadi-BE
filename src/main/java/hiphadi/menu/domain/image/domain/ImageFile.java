package hiphadi.menu.domain.image.domain;

import hiphadi.menu.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ImageFile extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 500, nullable = false)
	private String url;

	@Column(length = 500)
	private String originalFilename;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ImageType imageType;

	@Column(name = "header_display_order")
	private Integer headerDisplayOrder;  // null = not shown in header, number = display order

	@Builder
	public ImageFile(String url, String originalFilename, ImageType imageType) {
		this.url = url;
		this.originalFilename = originalFilename;
		this.imageType = imageType;
	}

	public void updateHeaderDisplayOrder(Integer order) {
		this.headerDisplayOrder = order;
	}
}
