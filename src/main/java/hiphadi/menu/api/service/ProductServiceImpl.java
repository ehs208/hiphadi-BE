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
import hiphadi.menu.domain.menuAvailability.Situation;
import hiphadi.menu.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;
	private final MenuAvailabilityRepository menuAvailabilityRepository;
	private final CurrentSituationRepository currentSituationRepository;
	@Transactional(readOnly = true)
	@Override
	public List<ProductListResponse> getAllProducts() {

		CurrentSituation situation = currentSituationRepository.findCurrentSituationByIsActive(true);

		return menuAvailabilityRepository.findBySituationOrderByCategoryPriorityAndPrice(situation.getSituation())
			.stream()
			.map(ProductListResponse::new)
			.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	@Override
	public ProductDetailResponse getProductDetail(Long id) {
		return new ProductDetailResponse(menuAvailabilityRepository.findById(id).orElseThrow());
	}
}
