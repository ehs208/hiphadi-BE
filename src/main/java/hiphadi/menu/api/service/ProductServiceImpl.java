package hiphadi.menu.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hiphadi.menu.api.service.request.ProductRequest;
import hiphadi.menu.api.service.response.ProductResponse;
import hiphadi.menu.domain.product.Product;
import hiphadi.menu.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;

	@Override
	public Void createProduct(ProductRequest productRequest) {
		Product product = Product.create(productRequest.getName(), productRequest.getDescription(),
			productRequest.getPrice(), productRequest.getCategory(), productRequest.getStatus());

		productRepository.save(product);

		return null;
	}

	@Override
	public Void deleteProduct(Long id) {
		productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));

		productRepository.deleteById(id);
		return null;
	}

	@Override
	public Void updateProduct(Long id, ProductRequest productRequest) {
		Product product = productRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));

		product.update(productRequest.getName(), productRequest.getDescription(), productRequest.getPrice(),
			productRequest.getCategory(), productRequest.getStatus());

		return null;
	}

	@Transactional(readOnly = true)
	@Override
	//미사용 서비스
	public List<ProductResponse> findProductsByPage(String categoryCursor, Long idCursor, Pageable pageable) {
		return getProducts(categoryCursor, idCursor, pageable)
			.stream()
			.map(ProductResponse::new)
			.collect(Collectors.toList());
	}

	//미사용 서비스
	private List<Product> getProducts(String categoryCursor, Long idCursor, Pageable pageable) {
		if (categoryCursor == null && idCursor == null) {
			// 커서가 없을 때는 모든 제품을 카테고리와 ID로 정렬하여 반환
			return productRepository.findAllByOrderByCategoryDescIdDesc(pageable);
		}
		// 커서가 있을 때는 categoryCursor와 idCursor를 사용하여 필터링
		return productRepository.findByCursor(categoryCursor, idCursor, pageable);
	}

	@Transactional(readOnly = true)
	@Override
	public List<ProductResponse> getAllProducts() {
		return productRepository.findAll()
			.stream()
			.map(ProductResponse::new)
			.collect(Collectors.toList());
	}
}
