package hiphadi.menu.api.service.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequest {
	private String username;
	private String password;
}
