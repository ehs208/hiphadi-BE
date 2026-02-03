package hiphadi.menu.domain.sitesetting.dto;

import java.util.Map;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdateSiteSettingsRequest {

	@NotNull
	private Map<String, String> settings;
}
