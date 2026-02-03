package hiphadi.menu.domain.sitesetting.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hiphadi.menu.domain.sitesetting.domain.SiteSetting;

public interface SiteSettingRepository extends JpaRepository<SiteSetting, String> {
}
