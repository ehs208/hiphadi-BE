package hiphadi.menu.domain.image.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hiphadi.menu.domain.image.dto.ImageFileResponse;
import hiphadi.menu.domain.image.service.ImageLibraryService;
import hiphadi.menu.global.response.GlobalResponseDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

	private final ImageLibraryService imageLibraryService;

	@GetMapping("/header")
	public GlobalResponseDto<List<ImageFileResponse>> getHeaderImages() {
		return GlobalResponseDto.success(imageLibraryService.getActiveHeaderImages());
	}
}
