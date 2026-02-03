package hiphadi.menu.domain.admin.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import hiphadi.menu.domain.image.domain.ImageType;
import hiphadi.menu.global.exception.CustomException;
import hiphadi.menu.global.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ImageService {

	private static final Set<String> ALLOWED_EXTENSIONS = Set.of(
		".jpg", ".jpeg", ".png", ".gif", ".webp", ".svg"
	);

	private static final Set<String> ALLOWED_CONTENT_TYPES = Set.of(
		"image/jpeg", "image/png", "image/gif", "image/webp", "image/svg+xml"
	);

	private final Path uploadDir;

	public ImageService(@Value("${upload.path:/var/www/hiphadi/images/}") String uploadPath) {
		this.uploadDir = Paths.get(uploadPath).toAbsolutePath().normalize();
		log.info("이미지 업로드 경로: {}", this.uploadDir);
	}

	public String uploadImage(MultipartFile file, ImageType imageType) {
		validateFile(file);

		final String extension = extractExtension(file.getOriginalFilename());
		final String newFilename = UUID.randomUUID() + extension;

		final String subDir = imageType == ImageType.HEADER ? "header" : "products";
		final Path targetDir = uploadDir.resolve(subDir);

		try {
			if (!Files.exists(targetDir)) {
				Files.createDirectories(targetDir);
			}

			final Path filePath = targetDir.resolve(newFilename);
			file.transferTo(filePath.toFile());

			log.info("이미지 업로드 완료: {}/{}", subDir, newFilename);
			return "/images/" + subDir + "/" + newFilename;
		} catch (IOException e) {
			log.error("이미지 업로드 실패: {}/{}", subDir, newFilename, e);
			throw new CustomException(ErrorCode.IMAGE_UPLOAD_FAILED);
		}
	}

	public void deleteImageFile(String url) {
		final String relativePath = url.replaceFirst("^/images/", "");
		final Path filePath = uploadDir.resolve(relativePath);

		try {
			Files.deleteIfExists(filePath);
			log.info("이미지 삭제 완료: {}", relativePath);
		} catch (IOException e) {
			log.warn("이미지 파일 삭제 실패: {}", relativePath, e);
		}
	}

	private void validateFile(MultipartFile file) {
		if (file.isEmpty()) {
			throw new CustomException(ErrorCode.FILE_EMPTY);
		}

		final String originalFilename = file.getOriginalFilename();
		final String extension = extractExtension(originalFilename);
		if (!ALLOWED_EXTENSIONS.contains(extension.toLowerCase())) {
			throw new CustomException(ErrorCode.INVALID_FILE_EXTENSION);
		}

		final String contentType = file.getContentType();
		if (contentType == null || !ALLOWED_CONTENT_TYPES.contains(contentType.toLowerCase())) {
			throw new CustomException(ErrorCode.INVALID_FILE_TYPE);
		}
	}

	private String extractExtension(String filename) {
		if (filename == null || filename.isBlank()) {
			throw new CustomException(ErrorCode.INVALID_FILENAME);
		}

		final int dotIndex = filename.lastIndexOf(".");
		if (dotIndex < 0 || dotIndex == filename.length() - 1) {
			throw new CustomException(ErrorCode.NO_FILE_EXTENSION);
		}

		return filename.substring(dotIndex);
	}
}
