package hiphadi.menu;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {

	@Test
	void encodePassword() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode("admin");
		System.out.println("암호화된 비밀번호: " + encodedPassword);
	}
}
