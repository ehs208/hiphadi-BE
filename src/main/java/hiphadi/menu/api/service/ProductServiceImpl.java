package hiphadi.menu.api.service;

import org.springframework.stereotype.Service;

import hiphadi.menu.domain.product.Product;
import hiphadi.menu.domain.product.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import hiphadi.menu.api.service.request.AddProductRequestDto;

@Transactional
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;

	@Override
	public void addProduct(AddProductRequestDto addProductRequestDto) {
		Product product = Product.create(addProductRequestDto.getName(), addProductRequestDto.getDescription(),
				addProductRequestDto.getPrice(), addProductRequestDto.getCategory(), addProductRequestDto.getStatus());

		productRepository.save(product);
	}

	@Override
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public void updateProduct(Long id, AddProductRequestDto addProductRequestDto) {
		Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));
		product.update(addProductRequestDto.getName(), addProductRequestDto.getDescription(), addProductRequestDto.getPrice(), addProductRequestDto.getCategory(), addProductRequestDto.getStatus());
	}
}
