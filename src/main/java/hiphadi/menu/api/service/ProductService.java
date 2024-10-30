package hiphadi.menu.api.service;

import hiphadi.menu.api.service.request.AddProductRequestDto;

public interface ProductService {
	void addProduct(AddProductRequestDto addProductRequestDto);
	void deleteProduct(Long id);
}
