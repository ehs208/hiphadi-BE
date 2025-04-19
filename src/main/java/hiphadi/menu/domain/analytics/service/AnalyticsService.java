package hiphadi.menu.domain.analytics.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.analytics.data.v1beta.BetaAnalyticsDataClient;
import com.google.analytics.data.v1beta.BetaAnalyticsDataSettings;
import com.google.analytics.data.v1beta.DateRange;
import com.google.analytics.data.v1beta.Dimension;
import com.google.analytics.data.v1beta.Metric;
import com.google.analytics.data.v1beta.OrderBy;
import com.google.analytics.data.v1beta.Row;
import com.google.analytics.data.v1beta.RunReportRequest;
import com.google.analytics.data.v1beta.RunReportResponse;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;

import hiphadi.menu.domain.analytics.dto.PopularMenuResponseDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

	@Value("${google.analytics.property-id}")
	private String propertyId;

	@Value("${google.analytics.client-email}")
	private String clientEmail;

	@Value("${google.analytics.private-key}")
	private String privateKey;

	public PopularMenuResponseDto getPopularMenus(String startDate, String endDate, int limit) {
		try (BetaAnalyticsDataClient client = initializeClient()) {

			RunReportRequest request = RunReportRequest.newBuilder()
				.setProperty("properties/" + propertyId)
				.addDateRanges(DateRange.newBuilder().setStartDate(startDate).setEndDate(endDate))
				.addDimensions(Dimension.newBuilder().setName("itemName"))
				.addMetrics(Metric.newBuilder().setName("itemsViewed"))
				.addOrderBys(OrderBy.newBuilder()
					.setMetric(OrderBy.MetricOrderBy.newBuilder().setMetricName("itemsViewed"))
					.setDesc(true))
				.build();

			RunReportResponse response = client.runReport(request);
			return parseReport(response, limit);

		} catch (IOException e) {
			throw new RuntimeException("GA4 인증 실패", e);
		}
	}

	private BetaAnalyticsDataClient initializeClient() throws IOException {
		// private key 문자열을 제대로 파싱
		String realPrivateKey = privateKey
			.replace("\\n", "\n")
			.replace("\"", "");

		GoogleCredentials credentials = ServiceAccountCredentials.fromPkcs8(
			null,
			clientEmail,
			realPrivateKey,
			null,
			null
		);

		BetaAnalyticsDataSettings settings = BetaAnalyticsDataSettings.newBuilder()
			.setCredentialsProvider(() -> credentials)
			.build();

		return BetaAnalyticsDataClient.create(settings);
	}

	private PopularMenuResponseDto parseReport(RunReportResponse response, int limit) {
		List<PopularMenuResponseDto.MenuInfo> menus = new ArrayList<>();
		int totalViews = 0;

		for (Row row : response.getRowsList()) {
			String itemName = row.getDimensionValues(0).getValue();
			int views = Integer.parseInt(row.getMetricValues(0).getValue());

			totalViews += views;

			menus.add(PopularMenuResponseDto.MenuInfo.builder()
				.name(itemName)
				.views(views)
				.build());
		}

		menus.sort((a, b) -> b.getViews() - a.getViews());

		return PopularMenuResponseDto.builder()
			.menus(menus.stream().limit(limit).toList())
			.totalViews(totalViews)
			.totalMenuCount(menus.size())
			.build();
	}
}
