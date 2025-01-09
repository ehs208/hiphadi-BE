package hiphadi.menu.domain.admin.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hiphadi.menu.domain.currentSituation.service.CurrentSituationService;
import hiphadi.menu.domain.menu.domain.Situation;
import hiphadi.menu.domain.menu.dto.MenuDetailResponse;
import hiphadi.menu.domain.menu.dto.MenuListResponse;
import hiphadi.menu.domain.menu.service.MenuService;
import hiphadi.menu.global.response.GlobalResponseDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/admin")
@RestController
public class AdminController {

	private final MenuService menuService;
	private final CurrentSituationService currentSituationService;

	@PostMapping("/login")
	public GlobalResponseDto<Void> login() {
		return GlobalResponseDto.success();
	}

	@GetMapping("/checkLogin")
	public GlobalResponseDto<Boolean> checkLogin(@AuthenticationPrincipal UserDetails userDetails) {
		boolean isAuthenticated = userDetails != null;
		return GlobalResponseDto.success(isAuthenticated);
	}

	@PatchMapping("{situation}")
	public GlobalResponseDto<Void> changeSituation(@PathVariable Situation situation) {
		currentSituationService.changeSituationStatus(situation);
		return GlobalResponseDto.success();
	}

	@GetMapping("")
	public GlobalResponseDto<Situation> getCurrentSituation() {
		return GlobalResponseDto.success(currentSituationService.getCurrentSituation());
	}

	@GetMapping("/menu/list/{situation}")
	public GlobalResponseDto<List<MenuListResponse>> getMenuListAdmin(@PathVariable String situation) {

		List<MenuListResponse> productListResponse = menuService.getAllMenusforAdmin(situation);
		return GlobalResponseDto.success(productListResponse);
	}

	@GetMapping("/menu/detail/{id}")
	public GlobalResponseDto<MenuDetailResponse> getMenuDetailAdmin(@PathVariable Long id) {
		return GlobalResponseDto.success(menuService.getMenuDetail(id));
	}
}
