package hiphadi.menu.domain.menu.controller;

import java.util.List;

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
		String ipAddress = getClientIpAddress(request);
		String userAgent = request.getHeader("User-Agent");
		
		menuService.recordVisit(ipAddress, userAgent);
		List<MenuListResponse> menuListResponse = menuService.getAllMenus();

		return GlobalResponseDto.success(menuListResponse);
	}

	@GetMapping("/detail/{id}")
	public GlobalResponseDto<MenuDetailResponse> getMenuInfo(@PathVariable Long id) {
		return GlobalResponseDto.success(menuService.getMenuDetail(id));
	}

	private String getClientIpAddress(HttpServletRequest request) {
		String xRealIp = request.getHeader("X-Real-IP");
		if (xRealIp != null && !xRealIp.isEmpty()) {
			return xRealIp;
		}
		
		String xForwardedFor = request.getHeader("X-Forwarded-For");
		if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
			return xForwardedFor.split(",")[0].trim();
		}
		
		return request.getRemoteAddr();
	}
}
