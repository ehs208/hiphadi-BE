package hiphadi.menu.api.service;
import java.util.List;

import org.springframework.data.domain.Pageable;

import hiphadi.menu.api.service.request.CreateProductDto;
import hiphadi.menu.api.service.response.ProductListDto;

public interface ProductService {
	void createProduct(CreateProductDto createProductDto);
	void deleteProduct(Long id);
	void updateProduct(Long id, CreateProductDto createProductDto);
	List<ProductListDto> findProductsByPage(String categoryCursor, Long idCursor, Pageable pageable);


}

