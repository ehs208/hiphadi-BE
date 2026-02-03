package hiphadi.menu.domain.image.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hiphadi.menu.domain.image.domain.ImageFile;
import hiphadi.menu.domain.image.domain.ImageType;

public interface ImageFileRepository extends JpaRepository<ImageFile, Long> {

	List<ImageFile> findAllByImageTypeOrderByCreatedAtDesc(ImageType imageType);

	List<ImageFile> findByHeaderDisplayOrderIsNotNullOrderByHeaderDisplayOrderAsc();

	Optional<ImageFile> findByUrl(String url);
}
