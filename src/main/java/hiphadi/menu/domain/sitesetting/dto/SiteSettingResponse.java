package hiphadi.menu.domain.sitesetting.dto;

import hiphadi.menu.domain.sitesetting.domain.SiteSetting;
import lombok.Getter;

@Getter
public class SiteSettingResponse {

	private String settingKey;
	private String settingValue;

	public SiteSettingResponse(SiteSetting siteSetting) {
		this.settingKey = siteSetting.getSettingKey();
		this.settingValue = siteSetting.getSettingValue();
	}
}
