package hiphadi.menu.domain.menu.controller;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hiphadi.menu.domain.menu.dto.MenuDetailResponse;
import hiphadi.menu.domain.menu.dto.MenuListResponse;
import hiphadi.menu.domain.menu.service.MenuService;
import hiphadi.menu.global.response.GlobalResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/menu")
@RestController
public class MenuController {

	private final MenuService menuService;

	@GetMapping("/list")
	public GlobalResponseDto<List<MenuListResponse>> getMenuList(HttpServletRequest request) {
		menuService.recordVisit(
			request.getHeader("X-Real-IP"),
			request.getHeader("User-Agent"));
		List<MenuListResponse> productListResponse = menuService.getAllMenus();

		return GlobalResponseDto.success(productListResponse);
	}

	@GetMapping("/detail/{id}")
	public GlobalResponseDto<MenuDetailResponse> getMenuInfo(@PathVariable Long id) {
		return GlobalResponseDto.success(menuService.getMenuDetail(id));
	}


}
