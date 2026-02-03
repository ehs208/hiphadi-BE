package hiphadi.menu.domain.image.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import hiphadi.menu.domain.admin.service.ImageService;
import hiphadi.menu.domain.image.domain.ImageFile;
import hiphadi.menu.domain.image.domain.ImageType;
import hiphadi.menu.domain.image.domain.repository.ImageFileRepository;
import hiphadi.menu.domain.image.dto.ImageFileResponse;
import hiphadi.menu.global.exception.CustomException;
import hiphadi.menu.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageLibraryService {

	private final ImageFileRepository imageFileRepository;
	private final ImageService imageService;

	@Transactional
	public ImageFileResponse uploadToLibrary(MultipartFile file, ImageType imageType) {
		final String url = imageService.uploadImage(file, imageType);
		final String originalFilename = file.getOriginalFilename();

		final ImageFile imageFile = ImageFile.builder()
			.url(url)
			.originalFilename(originalFilename)
			.imageType(imageType)
			.build();

		final ImageFile saved = imageFileRepository.save(imageFile);
		return new ImageFileResponse(saved);
	}

	@Transactional(readOnly = true)
	public List<ImageFileResponse> getImagesByType(ImageType imageType) {
		return imageFileRepository.findAllByImageTypeOrderByCreatedAtDesc(imageType)
			.stream()
			.map(ImageFileResponse::new)
			.collect(Collectors.toList());
	}

	@Transactional
	public void deleteImage(Long id) {
		final ImageFile imageFile = imageFileRepository.findById(id)
			.orElseThrow(() -> new CustomException(ErrorCode.IMAGE_NOT_FOUND));

		imageService.deleteImageFile(imageFile.getUrl());
		imageFileRepository.delete(imageFile);
	}

	@Transactional(readOnly = true)
	public List<ImageFileResponse> getActiveHeaderImages() {
		return imageFileRepository.findByHeaderDisplayOrderIsNotNullOrderByHeaderDisplayOrderAsc()
			.stream()
			.map(ImageFileResponse::new)
			.collect(Collectors.toList());
	}

	@Transactional
	public List<ImageFileResponse> updateHeaderImages(List<String> imageUrls) {
		// Clear all previous header display orders
		List<ImageFile> currentHeaders = imageFileRepository.findByHeaderDisplayOrderIsNotNullOrderByHeaderDisplayOrderAsc();
		for (ImageFile imageFile : currentHeaders) {
			imageFile.updateHeaderDisplayOrder(null);
		}

		// Set new header display orders
		List<ImageFile> newHeaders = new ArrayList<>();
		for (int i = 0; i < imageUrls.size(); i++) {
			final String url = imageUrls.get(i);
			final ImageFile imageFile = imageFileRepository.findByUrl(url)
				.orElseThrow(() -> new CustomException(ErrorCode.IMAGE_NOT_FOUND));
			imageFile.updateHeaderDisplayOrder(i + 1);
			newHeaders.add(imageFile);
		}

		return newHeaders.stream()
			.map(ImageFileResponse::new)
			.collect(Collectors.toList());
	}
}
