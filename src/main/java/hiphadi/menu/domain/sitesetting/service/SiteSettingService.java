package hiphadi.menu.domain.sitesetting.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hiphadi.menu.domain.sitesetting.domain.SiteSetting;
import hiphadi.menu.domain.sitesetting.domain.repository.SiteSettingRepository;
import hiphadi.menu.domain.sitesetting.dto.SiteSettingResponse;
import hiphadi.menu.domain.sitesetting.dto.UpdateSiteSettingsRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SiteSettingService {

	private final SiteSettingRepository siteSettingRepository;

	@Transactional(readOnly = true)
	public List<SiteSettingResponse> getAllSettings() {
		return siteSettingRepository.findAll()
			.stream()
			.map(SiteSettingResponse::new)
			.collect(Collectors.toList());
	}

	@Transactional
	public List<SiteSettingResponse> updateSettings(UpdateSiteSettingsRequest request) {
		request.getSettings().forEach((key, value) -> {
			SiteSetting setting = siteSettingRepository.findById(key)
				.orElse(null);

			if (setting != null) {
				setting.updateValue(value);
			} else {
				siteSettingRepository.save(new SiteSetting(key, value));
			}
		});

		return siteSettingRepository.findAll()
			.stream()
			.map(SiteSettingResponse::new)
			.collect(Collectors.toList());
	}
}
