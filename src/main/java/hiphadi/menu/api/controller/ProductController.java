package hiphadi.menu.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hiphadi.menu.api.ApiResponse;
import hiphadi.menu.api.service.ProductService;
import hiphadi.menu.api.service.response.ProductDetailResponse;
import hiphadi.menu.api.service.response.ProductListResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/product")
@RestController
public class ProductController {

	private final ProductService productService;

	@GetMapping("/list")
	public ApiResponse<List<ProductListResponse>> getProductList(HttpServletRequest request) {
		List<ProductListResponse> productListResponse = productService.getAllProducts(
			request.getHeader("X-Real-IP"),
			request.getHeader("User-Agent"));
		return ApiResponse.ok(productListResponse);
	}

	@GetMapping("/detail/{id}")
	public ApiResponse<ProductDetailResponse> getProductInfo(@PathVariable Long id) {
		return ApiResponse.ok(productService.getProductDetail(id));
	}
}
