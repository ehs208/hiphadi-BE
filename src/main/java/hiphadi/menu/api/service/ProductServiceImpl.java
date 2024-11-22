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

	@Transactional(readOnly = true)
	@Override
	public List<ProductResponse> getAllProducts() {
		return productRepository.findAll()
			.stream()
			.map(ProductResponse::new)
			.collect(Collectors.toList());
	}
}
