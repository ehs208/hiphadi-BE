package hiphadi.menu.api.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import hiphadi.menu.api.service.request.ProductRequest;
import hiphadi.menu.api.service.response.ProductResponse;

public interface ProductService {

	List<ProductResponse> getAllProducts();
}

