package hiphadi.menu.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hiphadi.menu.api.ApiResponse;
import hiphadi.menu.api.service.ProductServiceImpl;
import hiphadi.menu.api.service.response.ProductDetailResponse;
import hiphadi.menu.api.service.response.ProductListResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/product")
@RestController
public class ProductController {

	private final ProductServiceImpl productService;

	@GetMapping("/list")
	public ApiResponse<List<ProductListResponse>> getProductList() {
		List<ProductListResponse> productListResponse = productService.getAllProducts();
		return ApiResponse.ok(productListResponse);
	}

	@GetMapping("/detail/{id}")
	public ApiResponse<ProductDetailResponse> getProductInfo(@PathVariable Long id) {
		return ApiResponse.ok(productService.getProductDetail(id));
	}
}
