package hiphadi.menu.domain.admin.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hiphadi.menu.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/admin")
@RestController
public class AdminController {
	@PostMapping("/login")
	public ApiResponse<Void> login() {
		return ApiResponse.ok(null);
	}

	@GetMapping("/checkLogin")
	public ApiResponse<Boolean> checkLogin(@AuthenticationPrincipal UserDetails userDetails) {
		boolean isAuthenticated = userDetails != null;
		return ApiResponse.ok(isAuthenticated);
	}

}
