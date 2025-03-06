package hiphadi.menu.domain.menu.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hiphadi.menu.domain.menu.domain.Situation;
import hiphadi.menu.domain.menu.dto.MenuDetailResponse;
import hiphadi.menu.domain.menu.dto.MenuListResponse;
import hiphadi.menu.domain.currentSituation.domain.CurrentSituation;
import hiphadi.menu.domain.currentSituation.domain.repository.CurrentSituationRepository;
import hiphadi.menu.domain.menu.domain.repository.MenuRepository;
import hiphadi.menu.domain.visitLog.VisitLog;
import hiphadi.menu.domain.visitLog.VisitLogRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MenuService  {
	private final MenuRepository menuRepository;
	private final CurrentSituationRepository currentSituationRepository;
	private final VisitLogRepository visitLogRepository;

	@Transactional
	public void recordVisit(String ipAddress, String userAgent) {
		VisitLog visitLog = VisitLog.createVisitLog(null, ipAddress, userAgent);
		visitLogRepository.save(visitLog);
	}

	// 메뉴 조회만 담당하는 메서드 (캐싱됨)
	@Transactional(readOnly = true)
	@CacheEvict(value = "MENU", allEntries = true)
	public List<MenuListResponse> getAllMenus() {
		CurrentSituation situation = currentSituationRepository.findCurrentSituationByIsActive(true);

		return menuRepository.findBySituationOrderByCategoryPriorityAndPrice(situation.getSituation())
			.stream()
			.map(MenuListResponse::new)
			.collect(Collectors.toList());
	}


	@Transactional(readOnly = true)
	public MenuDetailResponse getMenuDetail(Long id) {
		return new MenuDetailResponse(menuRepository.findById(id).orElseThrow());
	}


	@Transactional
	public void getPopularMenus() {

	}


	//관리자 메뉴 조회
	@Transactional
	public List<MenuListResponse> getAllMenusforAdmin(String situation) {
		Situation situationEnum = Situation.valueOf(situation.toUpperCase());
		return menuRepository.findBySituationOrderByCategoryPriorityAndPrice(situationEnum)
			.stream()
			.map(MenuListResponse::new)
			.collect(Collectors.toList());
	}
}
