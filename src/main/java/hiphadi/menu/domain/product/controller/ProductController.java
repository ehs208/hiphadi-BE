package hiphadi.menu.domain.product.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hiphadi.menu.domain.product.dto.ProductDetailResponse;
import hiphadi.menu.domain.product.dto.ProductListResponse;
import hiphadi.menu.domain.product.service.ProductService;
import hiphadi.menu.domain.statistics.service.StatisticsService;
import hiphadi.menu.global.response.GlobalResponseDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/products")
@RestController
public class ProductController {

	private final ProductService productService;
	private final StatisticsService statisticsService;

	@GetMapping
	public GlobalResponseDto<List<ProductListResponse>> getProducts() {
		return GlobalResponseDto.success(productService.getAllProducts());
	}

	@GetMapping("/{id}")
	public GlobalResponseDto<ProductDetailResponse> getProductDetail(@PathVariable Long id) {
		statisticsService.recordProductClick(id);
		return GlobalResponseDto.success(productService.getProductDetail(id));
	}
}
