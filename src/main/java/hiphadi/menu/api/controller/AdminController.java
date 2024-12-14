package hiphadi.menu.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hiphadi.menu.api.ApiResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/admin")
@RestController
public class AdminController {
	@PostMapping("/login")
	public ApiResponse<Void> login() {
		// Spring Security가 자동으로 처리
		return ApiResponse.ok(null);
	}

}
