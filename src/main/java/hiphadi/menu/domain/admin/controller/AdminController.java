package hiphadi.menu.domain.admin.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hiphadi.menu.global.response.GlobalResponseDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/admin")
@RestController
public class AdminController {
	@PostMapping("/login")
	public GlobalResponseDto<Void> login() {
		return GlobalResponseDto.success();
	}

	@GetMapping("/checkLogin")
	public GlobalResponseDto<Boolean> checkLogin(@AuthenticationPrincipal UserDetails userDetails) {
		boolean isAuthenticated = userDetails != null;
		return GlobalResponseDto.success(isAuthenticated);
	}

}
