package hiphadi.menu.domain.analytics.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import hiphadi.menu.domain.analytics.dto.PopularMenuResponseDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

	private final RestTemplate restTemplate;

	@Value("${google.analytics.client-id}")
	private String clientId;

	@Value("${google.analytics.client-secret}")
	private String clientSecret;

	@Value("${google.analytics.refresh-token}")
	private String refreshToken;

	@Value("${google.analytics.property-id:470179832}")
	private String propertyId;

	public PopularMenuResponseDto getPopularMenus(String startDate, String endDate, int limit) {
		// 날짜가 제공되지 않으면 최근 7일로 설정
		if (startDate == null || endDate == null) {
			LocalDate today = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			endDate = formatter.format(today);
			startDate = formatter.format(today.minusDays(7));
		}

		// 1. 액세스 토큰 요청
		String accessToken = getAccessToken();

		// 2. 분석 데이터 요청
		Map<String, Object> analyticsData = callGoogleAnalyticsApi(accessToken, startDate, endDate);

		// 3. 결과 변환
		return extractPopularMenus(analyticsData, limit);
	}

	private String getAccessToken() {
		String url = "https://accounts.google.com/o/oauth2/token";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, String> body = new HashMap<>();
		body.put("client_id", clientId);
		body.put("client_secret", clientSecret);
		body.put("refresh_token", refreshToken);
		body.put("grant_type", "refresh_token");

		HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

		Map<String, Object> response = restTemplate.postForObject(url, entity, Map.class);
		return (String) response.get("access_token");
	}

	private Map<String, Object> callGoogleAnalyticsApi(String accessToken, String startDate, String endDate) {
		String url = "https://analyticsdata.googleapis.com/v1beta/properties/" + propertyId + ":runReport";

		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(accessToken);
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> body = new HashMap<>();
		body.put("dimensions", List.of(Map.of("name", "itemName")));
		body.put("metrics", List.of(Map.of("name", "itemsViewed")));
		body.put("dateRanges", List.of(Map.of("startDate", startDate, "endDate", endDate)));
		body.put("orderBys", List.of(Map.of("metric", Map.of("metricName", "itemsViewed"))));
		body.put("metricAggregations", List.of("TOTAL"));

		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

		return restTemplate.postForObject(url, entity, Map.class);
	}

	private PopularMenuResponseDto extractPopularMenus(Map<String, Object> analyticsData, int limit) {
		List<Map<String, Object>> rows = (List<Map<String, Object>>) analyticsData.get("rows");

		if (rows == null || rows.isEmpty()) {
			return PopularMenuResponseDto.builder()
				.menus(Collections.emptyList())
				.totalViews(0)
				.totalMenuCount(0)
				.build();
		}

		List<PopularMenuResponseDto.MenuInfo> menus = new ArrayList<>();

		for (Map<String, Object> row : rows) {
			List<Map<String, Object>> dimensions = (List<Map<String, Object>>) row.get("dimensionValues");
			List<Map<String, Object>> metrics = (List<Map<String, Object>>) row.get("metricValues");

			String name = ((String) dimensions.get(0).get("value")).trim();
			int views = Integer.parseInt((String) metrics.get(0).get("value"));

			menus.add(PopularMenuResponseDto.MenuInfo.builder()
				.name(name)
				.views(views)
				.build());
		}

		// 뷰 기준 내림차순 정렬
		menus.sort((a, b) -> b.getViews() - a.getViews());

		// 총 조회수 계산
		int totalViews = 0;
		List<Map<String, Object>> totals = (List<Map<String, Object>>) analyticsData.get("totals");
		if (totals != null && !totals.isEmpty()) {
			List<Map<String, Object>> metricValues = (List<Map<String, Object>>) totals.get(0).get("metricValues");
			totalViews = Integer.parseInt((String) metricValues.get(0).get("value"));
		} else {
			totalViews = menus.stream().mapToInt(PopularMenuResponseDto.MenuInfo::getViews).sum();
		}

		return PopularMenuResponseDto.builder()
			.menus(menus.stream().limit(limit).collect(Collectors.toList()))
			.totalViews(totalViews)
			.totalMenuCount(menus.size())
			.build();
	}
}
