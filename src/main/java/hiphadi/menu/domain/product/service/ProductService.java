package hiphadi.menu.domain.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hiphadi.menu.domain.product.dto.ProductDetailResponse;
import hiphadi.menu.domain.product.dto.ProductListResponse;
import hiphadi.menu.domain.currentSituation.domain.CurrentSituation;
import hiphadi.menu.domain.currentSituation.domain.repository.CurrentSituationRepository;
import hiphadi.menu.domain.menuAvailability.domain.repository.MenuAvailabilityRepository;
import hiphadi.menu.domain.product.domain.repository.ProductRepository;
import hiphadi.menu.domain.visitLog.VisitLog;
import hiphadi.menu.domain.visitLog.VisitLogRepository;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class ProductService  {
	private final ProductRepository productRepository;
	private final MenuAvailabilityRepository menuAvailabilityRepository;
	private final CurrentSituationRepository currentSituationRepository;
	private final VisitLogRepository visitLogRepository;

	@Transactional(readOnly = true)
	public List<ProductListResponse> getAllProducts(String ipAddress, String userAgent) {

		CurrentSituation situation = currentSituationRepository.findCurrentSituationByIsActive(true);

		VisitLog visitLog = VisitLog.createVisitLog(null, ipAddress, userAgent);
		visitLogRepository.save(visitLog);


		return menuAvailabilityRepository.findBySituationOrderByCategoryPriorityAndPrice(situation.getSituation())
			.stream()
			.map(ProductListResponse::new)
			.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public ProductDetailResponse getProductDetail(Long id) {
		return new ProductDetailResponse(menuAvailabilityRepository.findById(id).orElseThrow());
	}
}
