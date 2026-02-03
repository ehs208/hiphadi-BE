package hiphadi.menu.domain.statistics.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hiphadi.menu.domain.product.domain.Product;
import hiphadi.menu.domain.product.domain.repository.ProductRepository;
import hiphadi.menu.domain.statistics.domain.ProductClickDaily;
import hiphadi.menu.domain.statistics.domain.repository.ProductClickDailyRepository;
import hiphadi.menu.domain.statistics.dto.PopularMenusResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatisticsService {

	private final ProductClickDailyRepository productClickDailyRepository;
	private final ProductRepository productRepository;

	@Transactional
	public void recordProductClick(Long productId) {
		LocalDate today = LocalDate.now();
		productClickDailyRepository.upsertClickCount(productId, today);
	}

	@Transactional(readOnly = true)
	public PopularMenusResponse getPopularMenus(LocalDate startDate, LocalDate endDate, int limit) {
		List<ProductClickDaily> clicks = productClickDailyRepository
			.findByClickDateBetweenOrderByClickCountDesc(startDate, endDate);

		// Aggregate by productId
		Map<Long, Integer> clicksByProduct = new HashMap<>();
		for (ProductClickDaily click : clicks) {
			clicksByProduct.merge(click.getProductId(), click.getClickCount(), Integer::sum);
		}

		// Get product names
		Map<Long, String> productNames = productRepository.findAllById(clicksByProduct.keySet())
			.stream()
			.collect(Collectors.toMap(Product::getId, Product::getName));

		long totalClicks = clicksByProduct.values().stream().mapToLong(Integer::longValue).sum();

		List<PopularMenusResponse.MenuClickInfo> menus = clicksByProduct.entrySet().stream()
			.sorted((a, b) -> b.getValue().compareTo(a.getValue()))
			.limit(limit)
			.map(entry -> PopularMenusResponse.MenuClickInfo.builder()
				.productId(entry.getKey())
				.name(productNames.getOrDefault(entry.getKey(), "삭제된 메뉴"))
				.clicks(entry.getValue())
				.build())
			.collect(Collectors.toList());

		return PopularMenusResponse.builder()
			.menus(menus)
			.totalClicks(totalClicks)
			.totalMenuCount(clicksByProduct.size())
			.build();
	}

}
