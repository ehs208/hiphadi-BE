package hiphadi.menu.domain.product.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import hiphadi.menu.domain.product.service.ProductService;
import hiphadi.menu.domain.product.dto.ProductDetailResponse;
import hiphadi.menu.domain.product.dto.ProductListResponse;
import hiphadi.menu.global.response.GlobalResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/product")
@RestController
public class ProductController {

	private final ProductService productService;

	@GetMapping("/list")
	public GlobalResponseDto<List<ProductListResponse>> getProductList(HttpServletRequest request) {
		List<ProductListResponse> productListResponse = productService.getAllProducts(
			request.getHeader("X-Real-IP"),
			request.getHeader("User-Agent"));
		return GlobalResponseDto.success(productListResponse);
	}

	@GetMapping("/detail/{id}")
	public GlobalResponseDto<ProductDetailResponse> getProductInfo(@PathVariable Long id) {
		return GlobalResponseDto.success(productService.getProductDetail(id));
	}
}