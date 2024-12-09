package hiphadi.menu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApiKeyConfig {
	@Value("${api.key}")
	private String apiKey;

	public boolean isValidApiKey(String providedKey) {
		return apiKey.equals(providedKey);
	}
}
