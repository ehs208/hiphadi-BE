package hiphadi.menu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Spring 서버 전역적으로 CORS 설정
@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOriginPatterns("http://localhost:3000", "https://hiphadi.store", "https://dev-api.hiphadi.store", "https://api.hiphadi.store") // “*“같은 와일드카드를 사용
			.allowedMethods("GET", "POST") // 허용할 HTTP method
			.allowCredentials(true); // 쿠키 인증 요청 허용
	}
}
