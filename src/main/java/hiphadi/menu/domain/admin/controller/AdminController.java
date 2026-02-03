package hiphadi.menu.domain.admin.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import hiphadi.menu.domain.image.domain.ImageType;
import hiphadi.menu.domain.image.dto.ImageFileResponse;
import hiphadi.menu.domain.image.dto.UpdateHeaderImagesRequest;
import hiphadi.menu.domain.image.service.ImageLibraryService;
import hiphadi.menu.domain.product.domain.Category;
import hiphadi.menu.domain.product.dto.CategoryReorderRequest;
import hiphadi.menu.domain.product.dto.ProductReorderRequest;
import hiphadi.menu.domain.product.dto.CategoryRequest;
import hiphadi.menu.domain.product.dto.CreateProductRequest;
import hiphadi.menu.domain.product.dto.ProductDetailResponse;
import hiphadi.menu.domain.product.dto.ProductListResponse;
import hiphadi.menu.domain.product.dto.UpdateProductRequest;
import hiphadi.menu.domain.product.service.CategoryService;
import hiphadi.menu.domain.product.service.ProductService;
import hiphadi.menu.domain.statistics.dto.PopularMenusResponse;
import hiphadi.menu.domain.statistics.service.StatisticsService;
import hiphadi.menu.domain.sitesetting.dto.SiteSettingResponse;
import hiphadi.menu.domain.sitesetting.dto.UpdateSiteSettingsRequest;
import hiphadi.menu.domain.sitesetting.service.SiteSettingService;
import hiphadi.menu.domain.suggestion.dto.SuggestionResponse;
import hiphadi.menu.domain.suggestion.service.SuggestionService;
import hiphadi.menu.global.response.GlobalResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/admin")
@RestController
public class AdminController {

	private final ProductService productService;
	private final CategoryService categoryService;
	private final StatisticsService statisticsService;
	private final ImageLibraryService imageLibraryService;
	private final SuggestionService suggestionService;
	private final SiteSettingService siteSettingService;

	// Auth
	@PostMapping("/login")
	public GlobalResponseDto<Void> login() {
		return GlobalResponseDto.success();
	}

	@GetMapping("/checkLogin")
	public GlobalResponseDto<Boolean> checkLogin(@AuthenticationPrincipal UserDetails userDetails) {
		boolean isAuthenticated = userDetails != null;
		return GlobalResponseDto.success(isAuthenticated);
	}

	// Product CRUD
	@GetMapping("/products")
	public GlobalResponseDto<List<ProductListResponse>> getProducts() {
		return GlobalResponseDto.success(productService.getAllProducts());
	}

	@GetMapping("/products/{id}")
	public GlobalResponseDto<ProductDetailResponse> getProductDetail(@PathVariable Long id) {
		return GlobalResponseDto.success(productService.getProductDetail(id));
	}

	@PostMapping("/products")
	public GlobalResponseDto<ProductDetailResponse> createProduct(@Valid @RequestBody CreateProductRequest request) {
		return GlobalResponseDto.success(productService.createProduct(request), 201);
	}

	@PatchMapping("/products/{id}")
	public GlobalResponseDto<ProductDetailResponse> updateProduct(@PathVariable Long id,
																   @Valid @RequestBody UpdateProductRequest request) {
		return GlobalResponseDto.success(productService.updateProduct(id, request));
	}

	@DeleteMapping("/products/{id}")
	public GlobalResponseDto<Void> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return GlobalResponseDto.success();
	}

	@PatchMapping("/products/{id}/status")
	public GlobalResponseDto<ProductDetailResponse> toggleProductStatus(@PathVariable Long id) {
		return GlobalResponseDto.success(productService.toggleProductStatus(id));
	}

	@PatchMapping("/products/{id}/recommend")
	public GlobalResponseDto<ProductDetailResponse> toggleProductRecommend(@PathVariable Long id) {
		return GlobalResponseDto.success(productService.toggleProductRecommend(id));
	}

	@PatchMapping("/products/reorder")
	public GlobalResponseDto<Void> reorderProducts(@Valid @RequestBody ProductReorderRequest request) {
		productService.reorderProducts(request);
		return GlobalResponseDto.success();
	}

	// Category CRUD
	@GetMapping("/categories")
	public GlobalResponseDto<List<Category>> getCategories() {
		return GlobalResponseDto.success(categoryService.getAllCategories());
	}

	@PostMapping("/categories")
	public GlobalResponseDto<Category> createCategory(@Valid @RequestBody CategoryRequest request) {
		return GlobalResponseDto.success(categoryService.createCategory(request), 201);
	}

	@PatchMapping("/categories/{id}")
	public GlobalResponseDto<Category> updateCategory(@PathVariable Long id,
													  @Valid @RequestBody CategoryRequest request) {
		return GlobalResponseDto.success(categoryService.updateCategory(id, request));
	}

	@DeleteMapping("/categories/{id}")
	public GlobalResponseDto<Void> deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
		return GlobalResponseDto.success();
	}

	@PatchMapping("/categories/reorder")
	public GlobalResponseDto<Void> reorderCategories(@Valid @RequestBody CategoryReorderRequest request) {
		categoryService.reorderCategories(request);
		return GlobalResponseDto.success();
	}

	// Image Library
	@PostMapping("/images/upload")
	public GlobalResponseDto<ImageFileResponse> uploadImage(
		@RequestParam("file") MultipartFile file,
		@RequestParam("type") ImageType imageType) {
		return GlobalResponseDto.success(imageLibraryService.uploadToLibrary(file, imageType), 201);
	}

	@GetMapping("/images")
	public GlobalResponseDto<List<ImageFileResponse>> getImages(@RequestParam("type") ImageType imageType) {
		return GlobalResponseDto.success(imageLibraryService.getImagesByType(imageType));
	}

	@DeleteMapping("/images/{id}")
	public GlobalResponseDto<Void> deleteImage(@PathVariable Long id) {
		imageLibraryService.deleteImage(id);
		return GlobalResponseDto.success();
	}

	// Header Image Management
	@GetMapping("/header-images")
	public GlobalResponseDto<List<ImageFileResponse>> getActiveHeaderImages() {
		return GlobalResponseDto.success(imageLibraryService.getActiveHeaderImages());
	}

	@PutMapping("/header-images")
	public GlobalResponseDto<List<ImageFileResponse>> updateHeaderImages(
		@RequestBody UpdateHeaderImagesRequest request) {
		return GlobalResponseDto.success(imageLibraryService.updateHeaderImages(request.getImageUrls()));
	}

	// Statistics
	@GetMapping("/statistics/popular-menus")
	public GlobalResponseDto<PopularMenusResponse> getPopularMenus(
		@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
		@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
		@RequestParam(defaultValue = "10") int limit) {
		return GlobalResponseDto.success(statisticsService.getPopularMenus(startDate, endDate, limit));
	}

	// Suggestions
	@GetMapping("/suggestions")
	public GlobalResponseDto<List<SuggestionResponse>> getSuggestions() {
		return GlobalResponseDto.success(suggestionService.getAllSuggestions());
	}

	@DeleteMapping("/suggestions/{id}")
	public GlobalResponseDto<Void> deleteSuggestion(@PathVariable Long id) {
		suggestionService.deleteSuggestion(id);
		return GlobalResponseDto.success();
	}

	// Site Settings
	@GetMapping("/site-settings")
	public GlobalResponseDto<List<SiteSettingResponse>> getSiteSettings() {
		return GlobalResponseDto.success(siteSettingService.getAllSettings());
	}

	@PutMapping("/site-settings")
	public GlobalResponseDto<List<SiteSettingResponse>> updateSiteSettings(
		@Valid @RequestBody UpdateSiteSettingsRequest request) {
		return GlobalResponseDto.success(siteSettingService.updateSettings(request));
	}

}
