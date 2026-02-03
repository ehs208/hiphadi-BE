package hiphadi.menu.domain.product.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hiphadi.menu.domain.image.domain.ImageFile;
import hiphadi.menu.domain.image.domain.repository.ImageFileRepository;
import hiphadi.menu.domain.product.domain.Category;
import hiphadi.menu.domain.product.domain.Product;
import hiphadi.menu.domain.product.domain.repository.CategoryRepository;
import hiphadi.menu.domain.product.domain.repository.ProductRepository;
import hiphadi.menu.global.exception.CustomException;
import hiphadi.menu.global.exception.ErrorCode;
import hiphadi.menu.domain.product.dto.CreateProductRequest;
import hiphadi.menu.domain.product.dto.ProductDetailResponse;
import hiphadi.menu.domain.product.dto.ProductListResponse;
import hiphadi.menu.domain.product.dto.ProductReorderRequest;
import hiphadi.menu.domain.product.dto.UpdateProductRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	private final ImageFileRepository imageFileRepository;

	@Transactional(readOnly = true)
	@Cacheable(value = "MENU")
	public List<ProductListResponse> getAllProducts() {
		return productRepository.findAllOrderedByCategoryAndCustomOrder()
			.stream()
			.map(ProductListResponse::new)
			.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public ProductDetailResponse getProductDetail(Long id) {
		Product product = productRepository.findById(id)
			.orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
		return new ProductDetailResponse(product);
	}

	@Transactional
	@CacheEvict(value = "MENU", allEntries = true)
	public ProductDetailResponse createProduct(CreateProductRequest request) {
		Category category = categoryRepository.findById(request.getCategoryId())
			.orElseThrow(() -> new CustomException(ErrorCode.CATEGORY_NOT_FOUND));

		Product product = Product.builder()
			.name(request.getName())
			.engName(request.getEngName())
			.description(request.getDescription())
			.price(request.getPrice())
			.category(category)
			.build();

		Product saved = productRepository.save(product);

		if (request.getImageUrl() != null) {
			ImageFile imageFile = imageFileRepository.findByUrl(request.getImageUrl())
				.orElse(null);
			saved.updateImage(imageFile);
		}

		return new ProductDetailResponse(saved);
	}

	@Transactional
	@CacheEvict(value = "MENU", allEntries = true)
	public ProductDetailResponse updateProduct(Long id, UpdateProductRequest request) {
		Product product = productRepository.findById(id)
			.orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));

		Category category = product.getCategory();
		if (request.getCategoryId() != null) {
			category = categoryRepository.findById(request.getCategoryId())
				.orElseThrow(() -> new CustomException(ErrorCode.CATEGORY_NOT_FOUND));
		}

		product.updateInfo(
			request.getName() != null ? request.getName() : product.getName(),
			request.getEngName() != null ? request.getEngName() : product.getEngName(),
			request.getDescription() != null ? request.getDescription() : product.getDescription(),
			request.getPrice() != null ? request.getPrice() : product.getPrice(),
			category
		);

		if (request.getImageUrl() != null) {
			ImageFile imageFile = imageFileRepository.findByUrl(request.getImageUrl())
				.orElse(null);
			product.updateImage(imageFile);
		}

		return new ProductDetailResponse(product);
	}

	@Transactional
	@CacheEvict(value = "MENU", allEntries = true)
	public void deleteProduct(Long id) {
		Product product = productRepository.findById(id)
			.orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
		productRepository.delete(product);
	}

	@Transactional
	@CacheEvict(value = "MENU", allEntries = true)
	public ProductDetailResponse toggleProductStatus(Long id) {
		Product product = productRepository.findById(id)
			.orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
		product.toggleStatus();
		return new ProductDetailResponse(product);
	}

	@Transactional
	@CacheEvict(value = "MENU", allEntries = true)
	public ProductDetailResponse toggleProductRecommend(Long id) {
		Product product = productRepository.findById(id)
			.orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
		product.toggleRecommend();
		return new ProductDetailResponse(product);
	}

	@Transactional
	@CacheEvict(value = "MENU", allEntries = true)
	public void reorderProducts(ProductReorderRequest request) {
		List<Long> productIds = request.getProductIds();
		Map<Long, Product> productMap = productRepository.findAllById(productIds)
			.stream()
			.collect(Collectors.toMap(Product::getId, Function.identity()));

		for (int i = 0; i < productIds.size(); i++) {
			Product product = productMap.get(productIds.get(i));
			if (product == null) {
				throw new CustomException(ErrorCode.PRODUCT_NOT_FOUND);
			}
			product.updateCustomOrder((long) (i + 1));
		}
	}
}
