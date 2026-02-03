package hiphadi.menu.domain.sitesetting.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SiteSetting {

	@Id
	private String settingKey;

	@Column(columnDefinition = "TEXT")
	private String settingValue;

	public SiteSetting(String settingKey, String settingValue) {
		this.settingKey = settingKey;
		this.settingValue = settingValue;
	}

	public void updateValue(String settingValue) {
		this.settingValue = settingValue;
	}
}
