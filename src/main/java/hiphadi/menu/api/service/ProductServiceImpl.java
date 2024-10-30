package hiphadi.menu.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import hiphadi.menu.api.service.response.ProductListDto;
import hiphadi.menu.domain.product.Product;
import hiphadi.menu.domain.product.ProductRepository;
import jakarta.transaction.Transactional;
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

	@Override
	public List<ProductListDto> findProductsByPage(Long cursor, Pageable pageable) {
		return getProductList(cursor, pageable)
			.stream().map(ProductListDto::new).collect(Collectors.toList());
	}

	private List<Product> getProductList(Long cursor, Pageable pageable) {
		if (cursor == null) {
			return productRepository.findAllByOrderByCategoryDescIdDesc(pageable);
		} else {
			return productRepository.findByIdLessThanOrderByCategoryDescIdDesc(cursor, pageable);
		}
	}
}
