package hiphadi.menu.domain.admin.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hiphadi.menu.domain.admin.domain.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
	Optional<Admin> findByUsername(String username);
}

