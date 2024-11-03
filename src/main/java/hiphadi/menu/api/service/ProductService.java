package hiphadi.menu.api.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import hiphadi.menu.api.service.request.ProductRequest;
import hiphadi.menu.api.service.response.ProductResponse;

public interface ProductService {
	Void createProduct(ProductRequest productRequest);

	Void deleteProduct(Long id);

	Void updateProduct(Long id, ProductRequest productRequest);

	//미사용 서비스
	List<ProductResponse> findProductsByPage(String categoryCursor, Long idCursor, Pageable pageable);

	List<ProductResponse> getAllProducts();
}

