package hiphadi.menu.domain.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hiphadi.menu.domain.menu.dto.MenuDetailResponse;
import hiphadi.menu.domain.menu.dto.MenuListResponse;
import hiphadi.menu.domain.currentSituation.domain.CurrentSituation;
import hiphadi.menu.domain.currentSituation.domain.repository.CurrentSituationRepository;
import hiphadi.menu.domain.menu.domain.repository.MenuRepository;
import hiphadi.menu.domain.product.domain.repository.ProductRepository;
import hiphadi.menu.domain.visitLog.VisitLog;
import hiphadi.menu.domain.visitLog.VisitLogRepository;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class ProductService  {

	private final MenuRepository menuRepository;

	@Transactional(readOnly = true)
	public MenuDetailResponse getProductDetail(Long id) {
		return new MenuDetailResponse(menuRepository.findById(id).orElseThrow());
	}
}
