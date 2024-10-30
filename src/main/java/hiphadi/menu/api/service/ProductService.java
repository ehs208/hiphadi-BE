package hiphadi.menu.api.service;

import hiphadi.menu.api.service.request.CreateProductDto;

public interface ProductService {
	void createProduct(CreateProductDto createProductDto);
	void deleteProduct(Long id);
	void updateProduct(Long id, CreateProductDto createProductDto);
}
