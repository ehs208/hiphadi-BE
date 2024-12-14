package hiphadi.menu.api.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hiphadi.menu.domain.admin.Admin;
import hiphadi.menu.domain.admin.AdminRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService implements UserDetailsService {

	private final AdminRepository adminRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = adminRepository.findByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		return User.builder()
			.username(admin.getUsername())
			.password(admin.getPassword())
			.roles("ADMIN")
			.build();
	}
}
