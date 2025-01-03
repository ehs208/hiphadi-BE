package hiphadi.menu.domain.menu.service;

import java.util.List;
import java.util.stream.Collectors;

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

	@Transactional(readOnly = true)
	public List<MenuListResponse> getAllMenus(String ipAddress, String userAgent) {

		CurrentSituation situation = currentSituationRepository.findCurrentSituationByIsActive(true);

		VisitLog visitLog = VisitLog.createVisitLog(null, ipAddress, userAgent);
		visitLogRepository.save(visitLog);

		return menuRepository.findBySituationOrderByCategoryPriorityAndPrice(situation.getSituation())
			.stream()
			.map(MenuListResponse::new)
			.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public MenuDetailResponse getMenuDetail(Long id) {
		return new MenuDetailResponse(menuRepository.findById(id).orElseThrow());
	}

	// @Transactional
	// public void updateMenuDetail(Long id, MenuDetailResponse menuDetailResponse) {
	// 	menuRepository.findById(id).orElseThrow().update(menuDetailResponse);
	// }

	// @Transactional
	// public void deleteMenu(Long id) {
	// 	menuRepository.deleteById(id);
	// }
	//
	// @Transactional
	// public void createMenu(MenuDetailResponse menuDetailResponse) {
	// 	menuRepository.save(menuDetailResponse.toEntity());
	// }

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
