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
}
