package hiphadi.menu.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.cors(cors -> cors.configurationSource(corsConfigurationSource()))
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/api/situation/**").authenticated()  // situation 관련 요청만 인증 필요
				.anyRequest().permitAll()  // 나머지는 모두 허용
			)
			.sessionManagement(session -> session
				.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
				.maximumSessions(1)
				.maxSessionsPreventsLogin(false)  // false로 변경
			)
			.formLogin(login -> login
				.loginProcessingUrl("/api/admin/login")
				.usernameParameter("username")
				.passwordParameter("password")
				.successHandler((request, response, authentication) -> {
					HttpSession session = request.getSession();
					String origin = request.getHeader("Origin");

					Cookie sessionCookie = new Cookie("JSESSIONID", session.getId());
					sessionCookie.setPath("/");
					sessionCookie.setHttpOnly(true);
					sessionCookie.setSecure(true);  // HTTPS만 허용
					sessionCookie.setAttribute("SameSite", "None");  // 크로스 사이트 요청 허용

					response.addCookie(sessionCookie);
					response.setStatus(HttpStatus.OK.value());
				})
				.failureHandler((request, response, exception) -> {
					response.setStatus(HttpStatus.UNAUTHORIZED.value());
				})
			);

		return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "https://hiphadi.store", "https://dev-api.hiphadi.store", "https://api.hiphadi.store")); // 프론트엔드 주소
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
