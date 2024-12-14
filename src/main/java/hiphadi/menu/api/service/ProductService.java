package hiphadi.menu.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hiphadi.menu.api.service.response.ProductDetailResponse;
import hiphadi.menu.api.service.response.ProductListResponse;
import hiphadi.menu.domain.currentSituation.CurrentSituation;
import hiphadi.menu.domain.currentSituation.CurrentSituationRepository;
import hiphadi.menu.domain.menuAvailability.MenuAvailabilityRepository;
import hiphadi.menu.domain.product.ProductRepository;
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
