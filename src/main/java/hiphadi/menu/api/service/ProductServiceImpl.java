package hiphadi.menu.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import hiphadi.menu.api.service.response.ProductListDto;
import hiphadi.menu.domain.product.Product;
import hiphadi.menu.domain.product.ProductRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import hiphadi.menu.api.service.request.CreateProductDto;

@Transactional
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;

	@Override
	public void createProduct(CreateProductDto createProductDto) {
		Product product = Product.create(createProductDto.getName(), createProductDto.getDescription(),
				createProductDto.getPrice(), createProductDto.getCategory(), createProductDto.getStatus());

		productRepository.save(product);
	}

	@Override
	public void deleteProduct(Long id) {
		productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));

		productRepository.deleteById(id);
	}

	@Override
	public void updateProduct(Long id, CreateProductDto createProductDto) {
		Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));

		product.update(createProductDto.getName(), createProductDto.getDescription(), createProductDto.getPrice(), createProductDto.getCategory(), createProductDto.getStatus());
	}

	@Transactional(readOnly = true)
	@Override
	public List<ProductListDto> findProductsByPage(String categoryCursor, Long idCursor, Pageable pageable) {
		return getProducts(categoryCursor, idCursor, pageable)
			.stream()
			.map(ProductListDto::new)
			.collect(Collectors.toList());
	}


private List<Product> getProducts(String categoryCursor, Long idCursor, Pageable pageable) {
	if (categoryCursor == null && idCursor == null) {
		// 커서가 없을 때는 모든 제품을 카테고리와 ID로 정렬하여 반환
		return productRepository.findAllByOrderByCategoryDescIdDesc(pageable);
	}
	// 커서가 있을 때는 categoryCursor와 idCursor를 사용하여 필터링
	return productRepository.findByCursor(categoryCursor, idCursor, pageable);
}
}
