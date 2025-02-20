package hiphadi.menu.domain.product.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import hiphadi.menu.domain.product.service.ProductService;
import hiphadi.menu.domain.menu.dto.MenuDetailResponse;
import hiphadi.menu.domain.menu.dto.MenuListResponse;
import hiphadi.menu.global.response.GlobalResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/product")
@RestController
public class ProductController {

	private final ProductService productService;

	@GetMapping("/detail/{id}")
	public GlobalResponseDto<MenuDetailResponse> getProductInfo(@PathVariable Long id) {
		return GlobalResponseDto.success(productService.getProductDetail(id));
	}
}
