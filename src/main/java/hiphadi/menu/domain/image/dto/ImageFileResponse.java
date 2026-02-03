package hiphadi.menu.domain.image.dto;

import java.time.LocalDateTime;

import hiphadi.menu.domain.image.domain.ImageFile;
import lombok.Getter;

@Getter
public class ImageFileResponse {

	private final Long id;
	private final String url;
	private final String originalFilename;
	private final String imageType;
	private final Integer headerDisplayOrder;
	private final LocalDateTime createdAt;

	public ImageFileResponse(ImageFile imageFile) {
		this.id = imageFile.getId();
		this.url = imageFile.getUrl();
		this.originalFilename = imageFile.getOriginalFilename();
		this.imageType = imageFile.getImageType().name();
		this.headerDisplayOrder = imageFile.getHeaderDisplayOrder();
		this.createdAt = imageFile.getCreatedAt();
	}
}
