package hiphadi.menu.api.service;

import java.util.List;

import hiphadi.menu.api.service.response.ProductDetailResponse;
import hiphadi.menu.api.service.response.ProductListResponse;

public interface ProductService {

	List<ProductListResponse> getAllProducts();
	ProductDetailResponse getProductDetail(Long id);
}

