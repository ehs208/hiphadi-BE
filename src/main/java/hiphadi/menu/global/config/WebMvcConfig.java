package hiphadi.menu.global.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	private final String uploadPath;

	public WebMvcConfig(@Value("${upload.path:/var/www/hiphadi/images/}") String uploadPath) {
		this.uploadPath = uploadPath;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		Path absolutePath = Paths.get(uploadPath).toAbsolutePath().normalize();
		String resourceLocation = "file:" + absolutePath.toString() + "/";

		registry.addResourceHandler("/images/**")
			.addResourceLocations(resourceLocation);

		log.info("이미지 리소스 핸들러 등록: /images/** -> {}", resourceLocation);
	}
}
