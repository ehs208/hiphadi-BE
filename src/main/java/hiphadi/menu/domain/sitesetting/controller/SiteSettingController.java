package hiphadi.menu.domain.sitesetting.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hiphadi.menu.domain.sitesetting.dto.SiteSettingResponse;
import hiphadi.menu.domain.sitesetting.service.SiteSettingService;
import hiphadi.menu.global.response.GlobalResponseDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/site-settings")
@RequiredArgsConstructor
public class SiteSettingController {

	private final SiteSettingService siteSettingService;

	@GetMapping
	public GlobalResponseDto<List<SiteSettingResponse>> getAllSettings() {
		return GlobalResponseDto.success(siteSettingService.getAllSettings());
	}
}
